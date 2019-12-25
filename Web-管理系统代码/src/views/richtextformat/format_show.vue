<template>
<div class="app-container">
        <br>
            <el-select v-model="listQuery.region"  placeholder="请选择地域" @change="getListByRegion()">
                <!-- <el-option label="杭州市" value="杭州市"></el-option>
                <el-option label="其他" value="其他"></el-option> -->
                <el-option v-for="item in format_region" :key="item" :label="item" :value="item"/>
            </el-select>
            <el-select v-model="listQuery.type" placeholder="请选择文本类型" @change="getListByType()">
                <el-option v-for="item in format_type" :key="item" :label="item" :value="item"/>
            </el-select>
            <el-button type="primary" @click="getAllList()" >查找</el-button>
    <el-table 
    :data="destinationInfo" border fit highlight-current-row >
        <el-table-column
            label="文本编号" align="center"
            width="80">
            <template slot-scope="scope">
            {{ scope.row.destination_id }}
        </template>
        </el-table-column>
        
        <el-table-column
            label="地域" align="center"
            width="80">
            <template slot-scope="scope">
            {{ scope.row.region }}
        </template>
        </el-table-column>
        <el-table-column
            label="标签" align="center"
            width="180">
            <template slot-scope="scope">
            {{ scope.row.type }}
        </template>
        </el-table-column>
        <el-table-column
            label="类型" align="center"
            width="150px">
            <template slot-scope="scope">
            {{ scope.row.title }}
        </template>
        </el-table-column>
        <el-table-column
            label="作者" align="center"
            width="80">
            <template slot-scope="scope">
            {{ scope.row.editer }}
        </template>
        </el-table-column>
        <el-table-column
            label="作者头像" align="center"
            width="120">
            <template slot-scope="scope">
            <span><img :src="scope.row.e_image" width="100px" height="100"></span>
        </template>
        </el-table-column>
        <el-table-column label="介绍">
            <template slot-scope="scope">
                <span v-if="(scope.row.content.length>100)?contentxt=(scope.row.content).substring(0,100)+'...':contentxt=scope.row.content" v-html="contentxt" />
            </template>
        </el-table-column>

        <el-table-column
            label="上传时间" align="center"
            width="150">
            <template slot-scope="scope">
            {{ scope.row.time }}
        </template>
        </el-table-column>
        
    </el-table>
</div>

</template>
<script>
import { fetchList,findbytype,findbyregion,findbytyre } from '@/api/article'
export default {
    
    data() {
        return {
        format_type:[null,'旅游攻略','地域概况','演出节庆','历史故事','民俗风情','专题新闻','游记',],
        format_region:[null,'杭州市'],
        listQuery:{
            region:'',
            type:''
        },
        destinationInfo:null,
        contentxt:null,
    }
    },
    created() { 
        this.getList();
        
    },
    methods:{
        getList() {
            fetchList(this.listQuery).then(response => {
            this.destinationInfo =response
            // console.log(response)
        }
        )},
        getListByRegion(){
            findbyregion(this.listQuery.region).then(response =>{
                this.destinationInfo=response
                console.log("region",this.listQuery.region);
                console.log("response",response);
            })
        },
        getListByType(){
            findbytype(this.listQuery.type).then(response =>{
                this.destinationInfo=response
            })
        },
        getAllList()
        {
            findbytyre(this.listQuery.type,this.listQuery.region).then(response =>{
                this.destinationInfo=response
                // console.log('type+region====',response)
                console.log('region,type====',response)
            })
        },

    }
    
}     
</script>
