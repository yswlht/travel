<template>
  <div :id="id" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import resize from './mixins/resize'
import { statisticalProductByType, } from '@/api/count'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    id: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '600px'
    }
  },
  data() {
    return {
      chart: null,
      typeData:[],//订单统计类型   
    }
  },
  mounted() {
    this.initChart()
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  created(){
    this.getType();
  },
  methods: {
    getType(){
      statisticalProductByType().then(res =>{
        for(let i=0;i<res.length;i++){
          this.typeData[i]=res[i].type;
        }
        // console.log(this.typeData)
        // console.log(res);
        
      })
    },

    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))

      this.chart.setOption({
        backgroundColor: '#394056',
        title: {
          top: 20,
          text: '订单统计',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16,
            color: '#F1F1F3'
          },
          left: '1%'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            lineStyle: {
              color: '#57617B'
            }
          }
        },
        legend: {
          top: 20,
          icon: 'rect',
          itemWidth: 14,
          itemHeight: 5,
          itemGap: 13,
          data: ['门票','酒店','农家乐','寻美食','特产购买','导游预约','包租车'],//上标订单统计类型
          right: '4%',
          textStyle: {
            fontSize: 12,
            color: '#F1F1F3'
          }
        },
        grid: {
          top: 100,
          left: '2%',
          right: '2%',
          bottom: '2%',
          containLabel: true
        },
        xAxis: [{ //X坐标轴
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#57617B'
            }
          },
          //下标订单统计类型↓
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        }],
        yAxis: [{ //Y坐标轴
          type: 'value',
          name: '(%)',
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#57617B'
            }
          },
          axisLabel: {
            margin: 10,
            textStyle: {
              fontSize: 14
            }
          },
          splitLine: {
            lineStyle: {
              color: '#57617B'
            }
          }
        }],
        series: [
          //类型具体设置
          {
          name: '门票',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              //折线颜色↓
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0, 
                color: 'rgba(137, 189, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(137, 189, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              borderColor: 'rgba(137,189,2,0.27)',
              borderWidth: 12

            }
          },
          data: [220, 182, 191, 134, 150, 120, 110]
        },
          {
          name: '酒店',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              //折线颜色↓
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0, 
                color: 'rgba(89, 89, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(89, 89, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(89,89,27)',
              borderColor: 'rgba(89,89,2,0.27)',
              borderWidth: 12

            }
          },
          data: [150, 160, 170, 160, 150, 155, 170]
        },
          {
          name: '包租车',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              //折线颜色↓
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0, 
                color: 'rgba(255, 10, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(255, 10, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(255,10,27)',
              borderColor: 'rgba(255,10,2,0.27)',
              borderWidth: 12

            }
          },
          data: [100, 110, 120, 130, 120, 130, 155]
        },
          
          {
          name: '寻美食',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              //折线颜色↓
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0, 
                color: 'rgba(111, 222, 127, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(111, 222, 127, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(111,222,127)',
              borderColor: 'rgba(111,222,27,0.27)',
              borderWidth: 12

            }
          },
          data: [120, 185, 122, 156, 178, 150, 190]
        },
          {
          name: '特产购买',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              //折线颜色↓
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0, 
                color: 'rgba(235, 111, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(235, 111, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(235,111,27)',
              borderColor: 'rgba(235,111,2,0.27)',
              borderWidth: 12

            }
          },
          data: [230, 289, 291, 234, 250, 220, 210]
        },
        ]
      })
    }
  }
}
</script>
