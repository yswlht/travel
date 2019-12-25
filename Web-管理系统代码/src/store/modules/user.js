import { login, logout, getInfoadmin,getInfoeditor } from '@/api/login'
import Cookies from 'js-cookie'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { getPermission } from '../../api/login';

const user = {
  state: {
    token: '',
    name: '',
    password:'',
    avatar: '',
    roles: [],
    comId:'',
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_PASSWORD: (state, password) => {
      state.password = password
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_COMID:(state,comId) =>{
      state.comId=comId
    },
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      // console.log('用户名',username) // 用户名
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          // console.log('用户密码',userInfo.password) //用户密码
          const data = response
          // console.log('COMID',data[0]) //用户权限
          // console.log(setName(username))
          Cookies.set('admin',data[1])
          commit('SET_TOKEN',data[1]) 
          commit('SET_NAME',username)
          commit('SET_PASSWORD',userInfo.password)
          commit('SET_COMID',data[0])
          sessionStorage.setItem('com_id',data[0])
          sessionStorage.setItem('token',data[1])
          // setToken(data)
          resolve(response)
          // callbackify(username,userInfo.password)
        }).catch(error => {
          reject(error)
        })
      })
    },

    //获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        // console.log('state.token====',(state.token)) //admin  
        if(getPermission(state.token))
        {
          if(state.token=='admin'){
            getInfoadmin(state.token).then(responseadmin => {
              // console.log('response.token',responseadmin)
              if(!responseadmin){
                reject('Verification failed, please login again.')
              }
              const data = responseadmin
              // console.log('data====',responseadmin)
              if (data && data.length > 0) { // 验证返回的roles是否是一个非空数组
                commit('SET_ROLES', data)
              } else {
                reject('getInfo: roles must be a non-null array !')
              }
              commit('SET_NAME', state.name)
              commit('SET_PASSWORD',state.password)
              commit('SET_AVATAR', data.avatar)
              resolve(responseadmin)
            }).catch(error => {
              reject(error)
            })
          }
          else{
            if(state.token=='editor'){
              getInfoeditor(state.token).then(responseeditor => {
                if(!responseeditor){
                  reject('Verification failed, please login again.')
                }
                const dataE = responseeditor
                if (dataE && dataE.length > 0) { // 验证返回的roles是否是一个非空数组
                  commit('SET_ROLES', dataE)
                } else {
                  reject('getInfo: roles must be a non-null array !')
                }
                commit('SET_NAME', state.name)
                commit('SET_PASSWORD',state.password)
                commit('SET_AVATAR', dataE.avatar)
                resolve(responseeditor)
              }).catch(error => {
                reject(error)
              })
            }
          }
        }
        
      })
    },

    //登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

    //动态修改权限
    ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          dispatch('GenerateRoutes', data) // 动态修改权限后 重绘侧边菜单
          resolve()
        })
      })
    }
  }
}

export default user
