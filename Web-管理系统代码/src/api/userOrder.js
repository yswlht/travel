import request from '@/utils/request'

export function getList(comid){
    return request({
        url:'/findAllComOrder?com_id='+comid,
        method:'get',
    })
}

export function findAllComOrders(){
    return request({
        url:'/findAllComOrders',
        method:'get'
    })
}

export function searchOrder(comid,id){
    return request({
        url:'/searchComOrderById?com_id='+comid+'&order_id='+id,
        method:'get',
    })
}

export function deleteOrder(id){
    return request({
        url:'/deleteOrder?order_id='+id,
        method:'delete',
    })
}