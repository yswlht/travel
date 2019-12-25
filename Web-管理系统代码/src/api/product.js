import request from '@/utils/request'

export function getList(id){
    return request({
        url:'/findAllProduct?com_id='+id,
        method:'get',
    })
}

export function fandAllComProduct(){
    return request({
        url:'/findAllComProduct',
        method:'get',
    })
}

export function search(value){
    return request({
        url:'/searchProduct?search='+value,
        method:'get',
    })
}

export function deleteProduct(id){
    return request({
        url:'/deleteProduct?product_id='+id,
        method:'delete',
    })
}

export function updateProduct(data){
    return request({
        url:'/updateProduct',
        method:'put',
        data
    })
}

export function insertProduct(data){
    return request({
        url:'/insertProduct',
        method:'post',
        data
    })
}