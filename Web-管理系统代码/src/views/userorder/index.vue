<template>
    <!-- <el-tag>User-Order/用户订单管理</el-tag> -->
    <div class="app-container">
        <br>
            <el-input type="text" placeholder="请输入订单号"
            v-model="searchvalue"
            autofocus="true" @keyup.enter.native="searchData()">
            </el-input>
        <br>


        <el-table :data="list" border fit highlight-current-row >
            <el-table-column label="订单号">
                <template slot-scope="scope">
                    {{ scope.row.order_id }}
                </template>
            </el-table-column>
            <el-table-column label="商品号">
                <template slot-scope="scope">
                    {{ scope.row.shop_id }}
                </template>
            </el-table-column>
            <el-table-column label="用户ID">
                <template slot-scope="scope">
                    {{ scope.row.weixin_openid }}
                </template>
            </el-table-column>
            <el-table-column label="微信用户名">
                <template slot-scope="scope">
                    {{ scope.row.name }}
                </template>
            </el-table-column>
            <el-table-column label="订单类型" >
                <template slot-scope="scope">
                    {{ scope.row.type }}
                </template>
            </el-table-column>
            <el-table-column label="订单时间">
                <template slot-scope="scope">
                    {{ scope.row.datetime }}
                </template>
            </el-table-column>
            <el-table-column label="联系电话">
                <template slot-scope="scope">
                    {{ scope.row.tel }}
                </template>
            </el-table-column>
            <el-table-column label="用户备注">
                <template slot-scope="scope">
                    {{ scope.row.remarks }}
                </template>
            </el-table-column>
            <el-table-column label="商品数量">
                <template slot-scope="scope">
                    {{ scope.row.num }}
                </template>
            </el-table-column>
            <el-table-column label="核销状态">
                <template slot-scope="scope">
                   <el-tag :type="tagtype"> {{ scope.row.pin }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="发货状态">
                <template slot-scope="scope">
                   <el-tag type="danger"> {{ scope.row.delivery_status }} </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="发货方式">
                <template slot-scope="scope">
                    {{ scope.row.delivery_way }}
                </template>
            </el-table-column>
            <el-table-column label="总金额">
                <template slot-scope="scope">
                    {{ scope.row.money }}
                </template>
            </el-table-column>
            <el-table-column label="已支付">
                <template slot-scope="scope">
                    {{ scope.row.is_pay }}
                </template>
            </el-table-column>
            <el-table-column label="核销">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" :disabled="disabled"
                    @click="handlePin(scope.$index,scope.row)">核销</el-button>
                </template>
            </el-table-column>
            <!-- <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" :disabled="disabled"
                    @click="handleDelete(scope.$index,scope.row)">删除</el-button>
                </template>
            </el-table-column> -->
        </el-table>
    </div>
</template>

<script>
import { mapGetters,mapState } from 'vuex';
import { getList,searchOrder,deleteOrder,findAllComOrders } from '@/api/userOrder'
import Cookies from 'js-cookie'
import { type } from 'os';

export default {
    
    data(){
        return{
            list:[],
            searchvalue:'',
            disabled:false,
            tagtype:'parmary',
            token:sessionStorage.getItem('token'),
            com_id: sessionStorage.getItem('com_id')
        }
    },
    created(){
        this.fetchData()
    },
    methods:{
           
    //   console.log('comname====',comname)
        fetchData(){
            // console.log('comid',this.Cookies.get('comid'))
            if(this.token=='admin'){
                findAllComOrders().then(response =>{
                    this.list=response
                    this.disabled=true;
                })
            }else{
                getList(this.com_id).then(response =>{
                    this.list = response
                    // console.log(response)
                })
            }
        },
        //核销点击功能，弹出一个二维码进行核销
        handlePin(index,row){
            // if(this.token!='admin'){
            //     this.disabled=false;
            // }
            
        },
        handleDelete(index,row){
            // console.log(row.order_id)
            deleteOrder(row.order_id).then(response =>{
                if(response=='删除成功'){
                    this.fetchData();
                    this.disabled=true;
                }else{
                    this.disabled=false;
                }
            })
        },
        searchData(){
            searchOrder(this.com_id,this.searchvalue).then(response => {
                if(response.length != 0){
                    this.list = response
                }else{
                    alert('该订单不存在')
                }
            })
        }
    }
    
}
</script>

