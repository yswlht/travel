import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'

import Qs from 'Qs'
import { getToken } from '@/utils/auth'
// import { login } from '../api/login';
// import { userInfo } from 'os';

// 创建axios实例
const service = axios.create({
  baseURL: '/web', // api 的 base_url
  timeout: 5000, // 请求超时时间
  headers:{'content-type': 'application/x-www-form-urlencoded' },
  transformRequest: [function (data) {
    // 对 data 进行任意转换处理
    return Qs.stringify(data);
  }],
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['X-Token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Do something with request error
    console.log('报错',error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
   const res = response.data
   if(res.cide !=='200' && res.code !==200){
     if( res.code ==='4001' || res.code === 4001){
       MessageBox.confirm('用户名或密码错误，请重新登陆','重新登陆',{
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
       }).then(() => {
         store.dispatch('FedLogOut').then(() => {
           location.reload()
         })
       })
     }else if(res.code===500){
       MessageBox.confirm('未找到该用户名',{
         confirmButtonText:'确认',
         type:'warning',
       }).then(() =>{
         store.dispatch('FedLogOut').then(()=>{
           location.reload()
         })
       })
     }
    //  if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
    //   MessageBox.confirm(
    //     '你已被登出，可以取消继续留在该页面，或者重新登录',
    //     '确定登出',
    //     {
    //       confirmButtonText: '重新登录',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }
    //   ).then(() => {
    //     store.dispatch('FedLogOut').then(() => {
    //       location.reload() // 为了重新实例化vue-router对象 避免bug
    //     })
    //   })
    // }

   }
    /**
     * code为非20000是抛错 可结合自己业务进行修改
     */
    // const res = response.data
    // if (res.code !== 200) {
    // //   Message({
    // //     message: res.message,
    // //     type: 'error',
    // //     duration: 5 * 1000
    // //   })

    //   // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
    //   if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
    //     MessageBox.confirm(
    //       '你已被登出，可以取消继续留在该页面，或者重新登录',
    //       '确定登出',
    //       {
    //         confirmButtonText: '重新登录',
    //         cancelButtonText: '取消',
    //         type: 'warning'
    //       }
    //     ).then(() => {
    //       store.dispatch('FedLogOut').then(() => {
    //         location.reload() // 为了重新实例化vue-router对象 避免bug
    //       })
    //     })
    //   }
    //   return Promise.reject('error')
    // } else {
    //   return res
    // }
    return res
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: '未找到该用户名',
      type: 'error',
      duration: 1 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
