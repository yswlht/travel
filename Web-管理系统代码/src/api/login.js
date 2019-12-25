import request from '@/utils/request'

export function login(username,password) {
  return request({
    url:'http://localhost:9002/web/comLogin?username='+username+'&password='+password,
    method: 'get',
    data:{
      username,
      password,
    },
  })
}

export function getPermission(token){
  return request({
    url:'http://localhost:9002/web/comPermission?token='+token,
    method:'get',
  })
  
}

export function getInfoadmin(token) {
  return request({
    url: 'http://localhost:9002/web/comLogin?username=admin&password=admin',
    method: 'get',
    params: { token }
  })
}
export function getInfoeditor(token) {
  return request({
    url: 'http://localhost:9002/web/comLogin?username=editor&password=editor',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/login',
    method: 'post'
  })
}
