<template>
    <div class="app-container"> 
        <el-table 
        :data="listData"
        border 
        fit 
        highlight-current-row>
        <el-table-column label="ID">
            <template slot-scope="scope">
                {{ scope.row.com_id }}
            </template>
        </el-table-column>
        <el-table-column label="核销用户名">
            <template slot-scope="scope">
                {{ scope.row.username }}
            </template>
        </el-table-column>
        <el-table-column label="核销密码" type="password">
            <template slot-scope="scope">
                {{ scope.row.password }}
            </template>
        </el-table-column>
        <el-table-column label="是否核销">
            <template slot-scope="scope">
                <el-tag :type="danger">{{ scope.row.modify }}</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button @click="handlechange(scope.$index,scope.row)" size="mini" type="danger" :disabled="disabled">修改</el-button>
            </template>
        </el-table-column>
        </el-table>
        <el-dialog title="修改账号密码" :visible.sync="visible" width="300px">
            <el-form :model="Data">
                <el-form-item label="用户名">
                    <el-input v-model="Data.username" />
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input v-model="Data.password" />
                </el-form-item>
                <el-form-item >
                    <el-button @click="changeInfo()" type="danger" style="margin-left:90px">修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>


<script>
import { getcomPin,changePin, } from '@/api/comPin'


export default {

    data(){
        return{
            listData:[],
            danger:'danger',
            disabled:true,
            visible:false,
            com_id:sessionStorage.getItem('com_id'),
            token:sessionStorage.getItem('token'),
            Data:[],
        }
    },
    created(){
        if(this.token=='editor'){
            this.disabled=false;
        }
        this.fetchData()
        
        
    },
    methods:{
        handlechange(index,row){
            this.visible=true;
            this.Data=Object.assign({},row)
            // row.modify=true;
            // this.disabled=true;
            // this.danger='success';
            // // console.log('Data',this.Data)
            // this.$http.put("/web/updatePin",this.Data)
            // changePin(row.username,row.password,row.com_id).then(response =>{
                
            // })
            
            // console.log(row.modify)
        },
        changeInfo(){
            this.$http.put("/web/updatePin",this.Data).then(res =>{
                if(res.data=='用户名已存在'){
                    this.$message({
                        message:'该用户名已存在，请重新输入',
                        type:'warning',
                    })
                }else{
                    this.danger='success',
                    this.disabled=true,
                    this.visible=false,
                    this.$message({
                        message:'修改成功',
                        type:'success',
                    })
                }
            })
            // console.log('data',this.Data)
        },
        fetchData(){
            getcomPin(this.com_id).then(response =>{
                this.listData=response
                for(let i=0;i<response.length;i++){
                    if(response[i].modify){
                        this.danger='success';
                        this.disabled=true;
                    }
                    // console.log('listdata.modify',this.listData)
                }
                // if(response.modify==true){
                //     this.disabled=true
                //     this.danger='success'
                // }
                // console.log('response',response)
            })
        },
    }
}
</script>
