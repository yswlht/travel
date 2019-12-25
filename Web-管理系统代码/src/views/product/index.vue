<template>
    <div class="app-container">
      <br>
      <el-input type="text" placeholder="请输入商品名进行搜索"
      v-model="searchvalue"
      autofocus="true" @keyup.enter.native="searchData()">
      </el-input>
      <br>
      <el-button @click="showdialog" style="margin-top:5px;margin-bottom:5px" :disabled="disabled" type="success">商品添加</el-button>
      <br>
      <el-dialog title="商品添加" :visible.sync="visible">
        <el-form :model="insertData" size="mini">
          <el-form-item label="商品名称">
            <el-input v-model="insertData.name" />
          </el-form-item>
          <el-form-item label="商品类型">
            <el-input v-model="insertData.type" />
          </el-form-item>
          <el-form-item label="库存数量">
            <el-input v-model="insertData.inventory" />
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="insertData.prices" />
          </el-form-item>
          <el-form-item label="商品规格">
            <el-input v-model="insertData.params" />
          </el-form-item>
          <el-form-item label="商家地域">
            <el-input v-model="insertData.region" />
          </el-form-item>
          <el-form-item label="商户地址">
            <el-input v-model="insertData.place" />
          </el-form-item>
          <el-form-item label="商品介绍">
            <el-input v-model="insertData.introduct" />
          </el-form-item>
          <el-form-item>
            <el-upload class="upload-demo" 
            action="#" 
            :on-preview="handlePreview" 
            :on-remove="handleRemove"
            :file-list="insertimg"
            list-type="picture" 
            :before-remove="beforeremove" 
            :before-upload="beforeupload"
            accept=".jpg,.png,.JPG,.PNG,.jpeg,.JPEG"
            :http-request="uploadFile">
             <el-button size="small" type="primary">图片上传</el-button>
             <div slot="tip" class="el-upload_tip">图片大小不能超过2MB</div>
            </el-upload>
          </el-form-item>
          <el-button @click="insertproduct" size="mini" type="success">添加</el-button>
          <el-button @click="handlecancer" size="mini" type="danger">取消</el-button>
        </el-form>
      </el-dialog>

      <el-table
        :data="list" 
        border
        fit
        highlight-current-row>
        <el-table-column width="50px" align="center" label="ID">
          <template slot-scope="scope">
            {{ scope.row.product_id}}
          </template>
        </el-table-column>
        <el-table-column width="200px" align="center" label="商品名">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column width="150px" align="center" label="价格(RMB)">
          <template slot-scope="scope">
            <ol type="A" >
              <li v-for="item in scope.row.price.length" :key="item" >
                {{ scope.row.price[item-1]+'￥' }}
              </li>
            </ol>
          </template>
        </el-table-column>
        <el-table-column width="100px" align="center" label="月销售量(件)">
          <template slot-scope="scope">
            {{ scope.row.sales }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="地址">
          <template slot-scope="scope">
            {{ scope.row.place }}
          </template>
        </el-table-column>
        <el-table-column width="100" align="center" label="类型">
          <template slot-scope="scope">
            {{ scope.row.type }}
          </template>
        </el-table-column>
        <el-table-column width="100px" align="center" label="库存(个)">
          <template slot-scope="scope">
            {{ scope.row.inventory }}
          </template>
        </el-table-column>
        <el-table-column width="100px" align="center" label="折扣(%)">
          <template slot-scope="scope">
            {{ scope.row.discount }}
          </template>
        </el-table-column>
        <el-table-column width="200px" align="center" label="产品规格">
          <template slot-scope="scope">
            <ol type="A" >
              <li v-for="item in scope.row.param.length" :key="item" >
                {{ scope.row.param[item-1] }}
              </li>
            </ol>
          </template>
        </el-table-column>
        <el-table-column width="100px" align="center" label="地域">
          <template slot-scope="scope">
            {{ scope.row.region }}
          </template>
        </el-table-column>
        <el-table-column width="100px" align="center" label="商家名">
          <template slot-scope="scope">
            {{ scope.row.com_name }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="150px" >
            <template slot-scope="scope">
                <el-button size="mini" type="primary" :disabled="disabled"
                @click="handleEdit(scope.$index,scope.row)"
                >编辑</el-button>
                <el-button size="mini" type="danger" :disabled="disabled"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>


    <!-- <img :src="piclist[0]"> -->
    <!-- 嵌套对话框，外层为编辑详情，内层为更换图片详情,当前为外层↓ -->
    <el-dialog title="详情信息" :visible.sync="dialogVisible">
      <!-- 当前为内层 ↓ -->
      <!-- 上传图片到服务器里，有点难 -->
        

    <el-form ref="form" :model="form" label-width="120px" size="mini"
      height="500px" width="300px">
        <!-- <el-form-item label="商品ID">
          <el-input v-model="form.id" />
        </el-form-item> -->
        <el-form-item label="商品名">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="商品价格">
          <ol type="A">
            <li v-for="item in prisize" :key="item">
              <el-input v-model="form.price[item-1]" />
            </li>
          </ol>
        </el-form-item>
        <el-form-item label="商品规格">
          <ol type="A">
            <li v-for="item in paramsize" :key="item">
              <el-input v-model="form.param[item-1]" />
            </li>
          </ol>
        </el-form-item>
        <el-form-item label="商品类型">
          <el-input v-model="form.type" />
        </el-form-item>

        <!-- 详情信息里的图片走马灯 -->
        <el-form-item label="商品图片">

          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="item in piclist" :key="item">
              <img :src="item">
            </el-carousel-item>
          </el-carousel>
        </el-form-item>

        <el-form-item label="库存">
          <el-input v-model="form.inventory" />
        </el-form-item>
        <el-form-item label="折扣(%)">
          <el-input v-model="form.discount" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认</el-button>
          <el-button type="danger" @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>



<script>

import { getList,search,deleteProduct,updateProduct,fandAllComProduct } from '@/api/product'
import OSS from 'ali-oss'
import { type } from 'os';
import { error } from 'util';
import { request } from 'https';



    export default {
      components:{
      },
        data(){
            return {
               list: [],//所有信息
               searchvalue:'',//搜索用
               form:[],//存放点击行的数值放在对话框里
               prisize:[], //价格数组的长度
               paramsize:[],//规格
               picsize:[],//图片数组长度
               imgurl:[],
               insertimg:[],
               piclist:[],//存放点击行的图片地址
               filelist:[],//存放上传图片的信息
               dialogVisible: false,//动态对话框
               visible:false,//添加商品对话框
               disabled:true,//禁用
               insertData:{
                 com_id:'',
                 path:'',
                 tyep:'',//类型
                 inventory:'',//库存
                 prices:'',//价格
                 params:'',//规格
                 name:'',//名称
                 place:'',//地址
                 region:'',//地域
                 introduct:'',//介绍
               },
               token:sessionStorage.getItem('token'),
               comid:sessionStorage.getItem('com_id'),
               updateData:{
                  name:'',
                  type:'',
                  prices:[],
                  params:[],
                  inventory:'',
                  product_id:'',
                },
                instype:['门票','酒店','包租车','导游预约','农家乐','寻美食','特产购买'],
                rules:{
                  name:[ { required:true,message:'请输入商品名称',trigger:'blur'}],
                  type:[{ required:true,message:'请输入类型',trigger:'blur'},
                        { type:this.instype,message:"'门票','酒店','包租车','导游预约','农家乐','寻美食','特产购买'",trigger:'change' }
                  ],
                  inventory:[{ required:true,message:'不能未空',trigger:'blur'},
                            { type:'number',message:'必须为数字'}
                  ],
                  prices:[
                    { required:true,message:'不能为空'},
                    { type:'number',message:'必须为数字'}
                  ],
                  params:[
                    { required:true,message:'必填'}
                  ],
                  place:[ { required:true,message:'必填'}],
                  region:[{ required:true,message:'必填'}],
                  introduct:[{ required:true,message:'必填'}]

                }
            }
        },
        mounted(){


        },
        computed:{},
        created (){
          // console.log('com_id=======',this.comid);
          if(this.token=='editor'){
            this.disabled=false;
          }
            this.fetchData()
        },
        methods: {
          showdialog(){
            this.visible=true;
          },
          beforeremove(file,insertimg){
            return this.$confirm(`确定移除${ file.name}?`);
          },
          beforeupload(file){
            const isli2M = file.size / 1024 / 1024 <2
            if(!isli2M){
              this.$message.error('上传图片太大,请重新上传!')
            }
            return isli2M
          },
          handleRemove(file,insertimg){
            console.log(file,insertimg);
          },
          handlePreview(file){
            console.log(file);
          },
          uploadFile(item){
            let OSS = require('ali-oss')
            let URL=[]
            let ossclient = new OSS({
              region:'oss-cn-hangzhou',
              accessKeyId:'LTAImBzEk5NEhA5b',
              accessKeySecret:'X7IrqVCizmFMTTbe3yXVqNc9W6RGaY',
              bucket:'tianjiwen',
            })
            let key = 'productimg/'+item.file.name;
            ossclient.put(key,item.file).then(function (response){
              URL.push(response.url)
            })
            this.imgurl.push(URL)
          },
            handleEdit(index,row) {
              this.dialogVisible=true;//外层对话框
              this.form=Object.assign({},row)//存放点击的行信息
              this.prisize=this.form.price.length//存放价格数组的长度
              this.paramsize=this.form.param.length
              this.picsize=this.form.picture.length//存放图片数组的长度
              this.piclist=this.form.picture//存放图片的信息（地址）
            },
            handleDelete(index, row) {
              deleteProduct(row.product_id).then(response =>{
                this.$message({
                  message:'删除成功',
                  type:'success',
                })
              })
                this.fetchData()
                // console.log(index, row);
            },//删除
            fetchData(){
              if(this.token=='admin'){
                fandAllComProduct().then(response =>{
                  this.list=response
                })
              }else{
                getList(this.comid).then(response =>{
                  this.list = response
                  // console.log('findalllist',response)
                })
              }
            },
            onSubmit(){
              
              for(let j=0;j<this.form.param.length;j++){
                this.updateData.params=this.form.param.join();
              }
              for(let i=0;i<this.form.price.length;i++){
                this.updateData.prices=this.form.price.join()
              }
              // this.updateData.prices=this.form.price;
              this.updateData.name=this.form.name;
              this.updateData.type=this.form.type;
              this.updateData.inventory=this.form.inventory;
              this.updateData.product_id=this.form.product_id;
              // console.log(this.updateData)
              this.$http.put("/web/updateProduct",this.updateData).then(res=>{
                // console.log(res)
                if(res){
                  this.$message({ message:'修改成功',type:'success'});
                  this.dialogVisible=false;
                  this.fetchData();
                }else{
                  this.$message('修改失败');
                }
              })
              // updateProduct(this.updateData).then(response =>{
              //   console.log(response)
              // })
            },
            onCancel(){
              this.$message({
                message:'取消',
                type:'warning'
              })
              this.dialogVisible=false;
            },
            searchData(){
              search(this.searchvalue).then(searchres =>{
                this.list=searchres
              })
            },
          insertproduct(insertData){
            for(let i=0;i<this.imgurl.length;i++){
              this.insertData.path=this.imgurl.join("^")
            }
            // console.log('imgpath',this.insertData.path)
            this.insertData.com_id=this.comid
            this.$http.post("/web/insertProduct",{
                  ...this.insertData
              }).then(response =>{
                if(response){
                  this.fetchData();
                  this.$message({
                    message:'添加成功!',
                    type:'success',
                  })
                }
              })

            
            this.visible=false;
            
          },
          handlecancer(){
            this.visible=false;
            this.$message({
              message:'取消添加',
              type:'warning',
            })
          },

        },

}
</script>

<style>
  .ul{
    list-style-type: none
  }
  .li{
    list-style-type: none
  }
</style>

