import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  //{ path: '/Register',component:() => import('@/views/Register'),hidden:true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },


  {
    path:'/userorder',
    component:Layout,
    children:[
      {
        path:'index',
        name:'User-Order',
        component:() => import('@/views/userorder/index'),
        meta:{ title:'订单管理',icon:'dingdanguanli'} //可以加上权限
      }
    ]
  },
  
  {
    path:'/product',
    component:Layout,
    children:[
      {
        path:'index',
        name:'Product',
        component:() => import('@/views/product/index'),
        meta:{ title:'产品管理',icon:'chanpinguanli'} //可以加上权限
      }
    ]
  },

  {
    path:'/countAll',
    component:Layout,
    redirect:'/countAll/countUsers',
    name:'CountAll',
    meta:{ title:'数据统计',icon:'shujutongji'},
    children:[
      {
        path:'/countUsers',
        name:'CountUsers',
        component:() => import('@/views/countAll/countUsers'),
        meta:{ title:'用户每日统计',icon:'zhandianshujutongji'} //可以加上权限
      },
      {
        path:'/countOrder',
        name:'CountOrder',
        component:() =>import('@/views/countAll/countOrder'),
        meta:{ title:'订单统计',icon:'shujutongjizx'}
      },
      {
        path:'/countPrice',
        name:'CountPrice',
        component:()=> import('@/views/countAll/countPrice'),
        meta:{ title:'金额统计',icon:'shujutongjiy'}
      }
    ]
  },

  {
    path:'/destination',
    component:Layout,
    children:[
      {
        path:'index',
        name:'Destination',
        component:() => import('@/views/destination/index'),
        meta:{ title:'核销人员管理',icon:'guanli'} //可以加上权限
      }
    ]
  },

  {
    path: '/richtextformat',
    component: Layout,
    redirect:'/richtextformat/format_show',
    name:'Richtextformat',
    meta: { title: '富文本管理', icon: 'nested' },
    children: [
    {
    path: 'format_show',
    name: 'Format_show',
    component: () => import('@/views/richtextformat/format_show'),
    meta: { title: '富文本列表', icon: 'form' }
    },
    {
    path: 'format_add',
    name: 'Format_add',
    component: () => import('@/views/richtextformat/format_add'),
    meta: { title: '富文本发布', icon: 'form' }
    }
    ]
    },
  
]

export default new Router({
  //mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  
  
  
  // {
  //   path:'/productspe',
  //   component:Layout,
  //   children:[
  //     {
  //       path:'index',
  //       name:'ProductSpe',
  //       component:() => import('@/views/productspe/index'),
  //       meta:{ title:'ProductSpe',icon:'form'} //可以加上权限
  //     }
  //   ]
  // },
  
  {
    path: '/userm',
    component: Layout,
    redirect:'/userm/index',
    name:'Userm',
    meta: { title: '账户管理', icon: 'example' },
    children: [
    {
    path: 'index',
    name: 'Userm',
    component: () => import('@/views/userm/index'),
    meta: { title: '企业账户', icon: 'table' }
    },
    {
    path: 'index_wx_user',
    name: 'Index_wx_user',
    component: () => import('@/views/userm/index_wx_user'),
    meta: { title: '微信账户', icon: 'table' }
    },
    // {
    // path: 'add_comUser',
    // name: 'Add_comUser',
    // component: () => import('@/views/userm/add_comUser'),
    // meta: { title: '添加账户', icon: 'form', roles:['editor'] }
    // }
    ]
    },
    
    
    

  { path: '*', redirect: '/404', hidden: true }
]

