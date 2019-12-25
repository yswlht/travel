import request from '@/utils/request'

export function insertDestination(data){
    return request({
        url:'/insertDestination',
        method:'post',
        data
    })
}