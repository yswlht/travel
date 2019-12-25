import request from '@/utils/request'

export function fetchList() {
  return request({
    url: '/findAllDestination',
    method: 'get',
  })
}


export function findbytype(type){
  return request({
    url:'/findDestinationByType?type='+type,
    method:'get',
  })
}


export function findbyregion(region){
  return request({
    url:'/findDestinationByRegion?region='+region,
    method:'get',
  })
}

export function findbytyre(Type,Region){
  return request({
    url:'/findDestinationByTyRe?type='+Type+'&region='+Region,
    method:'get',
  })
}