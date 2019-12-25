<template>
  <div>
    <br>
    <el-input
      v-model="search"
      class="search"
      placeholder="搜索"/>
    <br>
  <el-table
    :data="tables"
    style="width: 100%"
    >
    <el-table-column
      sortable
      prop="com_id"
      label="#"
      >
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名"
     >
    </el-table-column>
    <el-table-column
      prop="password"
      label="密码"
      >
    </el-table-column>
    <el-table-column
      prop="audit"
      label="是否审核"
      >
    </el-table-column>
    <el-table-column
      prop="com_name"
      label="公司名"
     >
    </el-table-column>
    <el-table-column
      prop="place"
      label="地址"
      >
    </el-table-column>
    <el-table-column
      prop="tel"
      label="电话"
      >
    </el-table-column>
    <el-table-column
      prop="region"
      label="地域"
      >
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="150px">
      <template slot-scope="scope">
        <el-button size="small" type="primary" @click="handleUpdate(scope.row)">编辑</el-button>
        <el-button
          @click.native.prevent="handleDelete(scope.$index,scope.row.username)"
          type="danger"
          size="small">
          删除
        </el-button>

      </template>
    </el-table-column>
    
  </el-table>
  <el-dialog
      title="Tips"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">

      <el-form ref='dataForm'>
        <el-form-item label="用户名" prop="username" >
          <el-input v-model="temp.username"  placeholder="请输入用户名（登录用）"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input show-password v-model="temp.password"  placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="temp.tel" placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="公司名" prop="com_name">
          <el-input v-model="temp.com_name" placeholder="请输入公司名"></el-input>
        </el-form-item>
        <el-form-item label="公司地址" prop="place">
          <el-input v-model="temp.place" placeholder="请输入公司地址"></el-input>
        </el-form-item>
        <el-form-item label="地域" prop="region">
          <el-select v-model="temp.region" placeholder="请选择地域">
            <el-option label="杭州市" value="杭州市"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpdateClick()">更新</el-button>
     </span>
    </el-dialog>
  </div>
</template>



<script>
  export default {
    
    created() {
    this.$http.get('/web/findAll')
      .then ( res => {
        // console.log('res', res)
        this.userInfo = res.data
      }
    )
    .catch(error => console.log(error))
  },
//   methods: {
//       handleDelete(username){
//         mui.confirm("您确定删除吗?", ({ index })=>{
//           if (index == 1) {
//             this.$$http("web/comDelete", { username: username })
//           }
//         }
      
    

//   },
  data() {
      return {
        userInfo:[],
        search:'',
        dialogVisible: false,
        temp:{
          com_name: '',
          username: '',
          tel: '',
          region: '',
          place: '',
          password: ''
        }
      }
    },
    methods:{
            handleDelete(index,username){    
              var da = {"username": username }
                this.$http.delete("/web/comDelete", { params:da})
                .catch(error => console.log(error))
            this.tables()
                
      },
       handleClose(done) {
        this.$confirm('确定关闭?')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      // console.log(this.temp)
      this.dialogStatus = 'update'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
      handleUpdateClick(){
        var da = {
          "username":this.temp.username,
          "region":this.temp.region,
          "place": this.temp.place,
          "com_name": this.temp.com_name,
          "password": this.temp.password,
          "tel": this.temp.tel
        }
        this.$http.put("/web/updateInformation",da).then(res =>{
          this.tables();
        })
        this.dialogVisible = false
        // this.userInfo = Object.assign({},temp)
        
        // console.log(da)
      }
    },
    computed:{
      tables:function(){
        var search=this.search;
        if(search){
          return  this.userInfo.filter(function(dataNews){
            return Object.keys(dataNews).some(function(key){
              return String(dataNews[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        return this.userInfo
      }
    }
  }
</script>