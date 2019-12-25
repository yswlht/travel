<template>
  <div class="app-container">
    <!-- <el-table :data="list" border fit highlight-current-row>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.productId }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.begindate1 }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.enddate1 }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.invontory }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          {{ scope.row.param }}
        </template>
      </el-table-column>
      <el-table-column>
        <template>
          <el-button size="mini" type="primary" @click="onEdit" >编辑</el-button>
          <el-button size="mini" type="danger" @click="onDelete">删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->
      

<!-- 活动设置对话框  ↓ -->
    <el-dialog title="添加活动" :visible.sync="dialogVisible">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="活动名称">
            <el-input v-model="form.name"/>
          </el-form-item>
          <el-form-item label="活动开始时间">
            <el-col :span="11">
              <el-date-picker v-model="form.begindate1" type="date" placeholder="Pick a date" style="width: 100%;"/>
            </el-col>
            <el-col :span="2" class="line">-</el-col>
            <el-col :span="11">
              <el-time-picker v-model="form.begindate2" type="fixed-time" placeholder="Pick a time" style="width: 100%;"/>
            </el-col>
          </el-form-item>
          <el-form-item label="活动结束时间">
            <el-col :span="11">
              <el-date-picker v-model="form.enddate1" type="date" placeholder="Pick a date" style="width: 100%;"/>
            </el-col>
            <el-col :span="2" class="line">-</el-col>
            <el-col :span="11">
              <el-time-picker v-model="form.enddate2" type="fixed-time" placeholder="Pick a time" style="width: 100%;"/>
            </el-col>
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price"/>
          </el-form-item>
          <el-form-item label="库存">
            <el-input v-model="form.invontory"/>
          </el-form-item>
          <el-form-item label="折扣">
            <el-radio-group v-model="form.type">
              <el-radio-button label="95折" name="type"/>
              <el-radio-button label="9折" name="type"/>
              <el-radio-button label="85折" name="type"/>
              <el-radio-button label="8折" name="type"/>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="产品规格设置">
            <el-input v-model="form.param" />
          </el-form-item>
          <el-form-item label="活动描述">
            <el-input v-model="form.desc" type="textarea"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">Create</el-button>
            <el-button @click="onCancel">Cancel</el-button>
          </el-form-item>
        </el-form>
    </el-dialog>
    </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        invontory: '',
        begindate1: '',
        begindate2: '',
        enddate1:'',
        enddate2:'',
        price: '',
        type: '',
        param: '',
        desc: ''
      },
      list:[],
      dialogVisible:true,
    }
  },
  methods: {
    onEdit(index,row){
      this.dialogVisible=true;
    },
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

