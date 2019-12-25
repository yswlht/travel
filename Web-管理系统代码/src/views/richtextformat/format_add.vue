<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
    <div class="createPost-main-container">
      <el-row>

        <el-col :span="24">

          <el-form-item prop="title">
            <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
              标题
            </MDinput>
          </el-form-item>

          <div class="postInfo-container">
            <el-row>


              <el-col :span="6">
                <el-form-item label-width="45px" label="作者头像" class="postInfo-container-item">
                  <img :src="postForm.e_image" width="50px" height="50px">
                </el-form-item>
              </el-col>


              <el-col :span="8">
                <el-form-item label-width="45px" label="作者:" class="postInfo-container-item">
                  <el-input v-model="postForm.editer" />
                </el-form-item>
              </el-col>


              <el-col :span="8">
                <el-form-item class="postInfo-container-item" label-width="45px" label="地域:">
                  <el-input v-model="postForm.region" />
                </el-form-item>
              </el-col> 

              <el-col :span="8">
                <el-form-item class="postInfo-container-item" label-width="45px" label="景点:">
                  <el-input v-model="postForm.poi" />
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item class="postInfo-container-item" label-width="45px" label="类型:">
                  <el-select v-model="postForm.type" placeholder="请选择">
                    <el-option v-for="(item) in type" :key="item.value" :label="item.label" :value="item.label" />
                  </el-select>
                </el-form-item>
              </el-col>

            </el-row>
            <div>
                  <el-upload
                    action="#"
                    list-type="picture-card"
                    :http-request="uploadFile"
                    accept=".jpg,.jpeg,.png,.JPG,.JPEG"
                    :on-preview="handlePictureCardPreview"
                    :before-upload="beforeUpload"
                    :on-remove="handleRemove"
                    :limit="3">
                    <i class="el-icon-plus"></i>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                  </el-dialog>
              </div>
          </div>
        </el-col>
      </el-row>

      
    </div>
    <div class="createPost-main-container">
      <el-form-item prop="content" style="margin-bottom: 30px;">
        <Tinymce ref="editer" v-model="postForm.content" :height="400" />
      </el-form-item>
    </div>
    <div class="text-center">
      <el-button style="margin-left: 10px;margin-bottom:30px" type="success" @click="submitForm">发布</el-button>
      <!-- <el-button style="margin-left: 10px;margin-bottom:30px" type="warning" @click="draftForm">草稿</el-button> -->
    </div>
    <!-- eslint-disable-next-line -->
    <!-- <div class="editor-content" v-html="content" /> -->
    </el-form>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import MDinput from '@/components/Mdinput'
import OSS from 'ali-oss'
import { insertDestination } from '@/api/format_add'

const defaultForm ={
  region:'',//地域 必传
  poi:'',//相关景点
  type:'',//类型
  title:'',//文章标题 必传
  path:'',//文章图片链接
  content:'',//文章内容 必传
  // content_short:'',//文章摘要
  editer:'',//作者 必传
  e_image:'',//作者头像 必传
  
}


export default {
  name: 'TinymceDemo',
  components: { Tinymce,MDinput },
  props:{
    isEdit:{
      type:Boolean,
      default:false,
    },
    value:{
      type:String,
      default:'',
    },
  },
  data() {
    const validateRequire =(rule,value,callback) =>{
      if(value ===''){
        this.$message({
          message:rule.filed+'为必传项',
          type:'error'
        })
        callback(new Error(rule.filed+'为必传项'))
      }else{
        callback()
      }
    }
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      imgUrl:[],
      postForm: Object.assign({},defaultForm ),

      rules:{
        title:[{ validator: validateRequire }],
        content:[{ validator: validateRequire }],
        type:[{ validator: validateRequire }],
        region:[{ validator: validateRequire }],
        editer:[{ validator: validateRequire }],
        e_image:[{ validator: validateRequire }],
      },
      type:[
          {
            value:'选项1',
            label:'旅游攻略',
        },{
          value:'选项2',
          label:'地域概况',
        },{
          value:'选项3',
          label:'演出节庆',
        },
        {
          value:'选项5',
          label:'历史故事',
        },{
          value:'选项6',
          label:'专题新闻',
        },{
          value:'选项7',
          label:'游记',
        },{
          value:'选项4',
          label:'民俗风情',
        }
      ],//文章类型 必传
      tempRoute:{}
    }
  },
  created(){
    this.postForm = Object.assign({},defaultForm)
  },
  methods:{

    beforeUpload(file){
      const isLt5M = file.size / 1024 / 1024 <5
      if(!isLt5M){
        this.$message.error('上传图片大小不能超过 5MB!')
      }
      return isLt5M
    },
    uploadFile(item){
      let OSS = require('ali-oss')
      let URL=[]
      let ossclient = new OSS({
        region:'oss-cn-hangzhou',
        accessKeyId:'LTAImBzEk5NEhA5b',
        accessKeySecret:'X7IrqVCizmFMTTbe3yXVqNc9W6RGaY',
        bucket:'tianjiwen'
      })
      let key = 'images/'+item.file.name
      // console.log('key===',key)
      ossclient.put(key,item.file).then(function (response){
        URL.push(response.url)
        // console.log('url===',response)
      })
      this.imgUrl.push(URL)
      // console.log('imgUrl===',this.imgUrl)
    },

    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    draftForm() {
      //草稿
    },
    submitForm(postForm) {
      //发布
      // console.log('---------------------------------')
      // console.log(this.postForm)
      for(var i=0;i<this.imgUrl.length;i++){
        this.postForm.path=this.imgUrl.join("^")
      }
      // console.log('picture===',this.postForm.picture)
      this.$refs.postForm.validate(valid =>{
        if(valid){
          //接口
          this.$http.post("/web/insertDestination",{
            ...this.postForm
          }).then(res =>{
            this.$router.push({ path:'/richtextformat'});
          })
        }
        else{
          console.log('error')
        }
      })
    },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "~@/styles/mixin.scss";
.createPost-container {
  position: relative;
  .createPost-main-container {
    padding: 40px 45px 20px 50px;
    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;
      .postInfo-container-item {
        float: left;
      }
    }
  }
  .word-counter {
    width: 40px;
    position: absolute;
    right: -10px;
    top: 0px;
  }
  .editor-content{
    margin-top: 20px;
  }
  .components-container {
    margin: 30px 50px;
    position: relative;
  }
  .text-center{
    margin-left: 50px;
  }
}
</style>

