<template>
  <div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm" >
      <br>
      <br>
      <el-form-item label="用户名" prop="username" >
        <el-input v-model="ruleForm.username"  placeholder="请输入用户名（登录用）"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input show-password v-model="ruleForm.password"  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="password_check">
        <el-input show-password v-model="ruleForm.password_check" placeholder="请确认密码" ></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="tel">
        <el-input v-model="ruleForm.tel" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="公司名" prop="com_name">
        <el-input v-model="ruleForm.com_name" placeholder="请输入公司名"></el-input>
      </el-form-item>
      <el-form-item label="公司地址" prop="place">
        <el-input v-model="ruleForm.place" placeholder="请输入公司地址"></el-input>
      </el-form-item>
      <el-form-item label="地域" prop="region">
        <el-select v-model="ruleForm.region" placeholder="请选择地域">
          <el-option label="杭州市" value="杭州市"></el-option>
          <el-option label="其他" value="其他"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addUser(ruleForm)">添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default {
    name:'Register',
    data() {
      return {
        ruleForm: {
          username: '',
          region: '杭州市',
          place: '',
          com_name: '',
          password: '',
          tel:''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 8, message: '长度为3到8', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 13, message: '长度为6到13', trigger: 'blur' }
            
          ],
          password_check: [
            { required: true, message: '请确认密码', trigger: 'blur' },
            { min: 6, max: 13, message: '长度为6到13', trigger: 'blur' },
            {
              validator:(rule,value,callback)=>{
              if(value===''){
              callback(new Error('请再次输入密码'))
              }else if(value!==this.ruleForm.password){
              callback(new Error('两次输入密码不一致'))
              }else{
              callback( )
              }
              },
              trigger:'blur'
              }
          ],
          com_name:[
            { required:true,message:'请输入公司名称', trigger:'blur' }
          ],
          place:[
            { required:true,message:'请输入公司地址', trigger:'blur' }
          ],
          region: [
            { required: true, message: '请选择地域', trigger: 'change' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      addUser(ruleForm){
        var da = {
          "username": ruleForm.username,
          "region": ruleForm.region,
          "place": ruleForm.place,
          "com_name": ruleForm.com_name,
          "password": ruleForm.password,
          "tel": ruleForm.tel
        }
        this.$http.post("/web/comRegister",da).then(res =>{
          this.$router.push({ path:'login' })
        })
        
        console.log(da)
        
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>