import request from '@/utils/request'

export function statisticalProductByType(){
    return request({
        url:'/statisticalProductByType',
        method:'get',
    })
}