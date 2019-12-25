import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { Message } from 'element-ui'
import { getName,getToken } from '@/utils/auth' // getToken from cookie
import permission from './store/modules/permission';
import { access } from 'fs';

NProgress.configure({ showSpinner: false })// NProgress configuration

// function hasPermission(roles,permissionRoles){ 动态改变权限
//   if(roles.includes('admin')) return true //直接传递管理员权限
//   if(!permissionRoles) return true
//   return roles.some(role => permissionRoles.indexOf(role) >=0)
// }

const whiteList = ['/login'] // 不重定向白名单

router.beforeEach((to, from, next) => {
  // NProgress.start() //开始进度条
   if (store.getters.token) {
     if (to.path === '/login') {
       next({ path: '/'});
      //  NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
   } 
   else {
      if (store.getters.roles.length === 0) { //===0表示没有拉取完用户信息
        store.dispatch('GetInfo').then(res => {
          const roles = res;
          store.dispatch('GenerateRoutes',{roles}).then(accessRoutes =>{
            router.addRoutes(accessRoutes)
            next({ ...to, replace: true })
          })
        }).catch(err => {
          console.log(err)
        });
      }else{
        next()
      }
    }
  }else {
    if(whiteList.indexOf(to.path)!==-1){
      next()
    }else{
      next({ path:'/login'}) // 否则全部重定向到登录页
      // NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
