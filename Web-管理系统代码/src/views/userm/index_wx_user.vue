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
    max-height="600">
    <el-table-column
      sortable
      prop="user_id"
      label="#"
      width="80">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="last_logintime"
      label="最近一次登录时间"
      width="150">
    </el-table-column>
    <el-table-column
      prop="tel"
      label="手机号"
      width="180">
    </el-table-column>
  </el-table></div>
</template>

<script>
  export default {
    
    created() {
    this.$http.get('/wx/findAll')
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
        search:''
      }
    },
    // methods:{
    //         handleDelete(index,username){    
    //           this.userInfo.splice(index, 1)
    //           var da = {"username": username }
    //             this.$http.delete("web/comDelete", { params:da})
    //             .catch(error => console.log(error))
                
    //   }
    // },
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