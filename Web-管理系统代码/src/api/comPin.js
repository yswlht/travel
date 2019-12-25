import request from '@/utils/request'

export function getcomPin(comid){
    return request({
        url:'/findOnePin?com_id='+comid,
        method:'get'
    })
}

export function changePin(name,pwd,id){
    return request({
        url:'/updatePin?username='+name+'&password='+pwd+'&com_id='+id,
        method:'put',
    })
}