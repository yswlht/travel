<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name:{{ name }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles',
    ])
  },
  created(){
    this.getsession()
  },
  methods:{
    getsession(){
      let comname = sessionStorage.getItem('comname')
      // console.log('comname====',comname)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
