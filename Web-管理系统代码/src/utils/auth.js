import Cookies from 'js-cookie'

const TokenKey = 'admin'

export function setName(username){
  return Cookies.set('name',username)
}

export function getName(){
  return Cookies.get('name')
}

export function setPassword(password){
  return Cookies.set('password',password)
}

export function getPassword(){
  return Cookies.get('password')
}

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
