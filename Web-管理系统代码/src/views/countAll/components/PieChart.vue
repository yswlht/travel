<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '900px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart()
    this.__resizeHandler = debounce(() => {
      if (this.chart) {
        this.chart.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '20',
          data: ['门票', '酒店', '包租车', '导游预约', '农家乐','寻美食','特产购买']
        },
        calculable: true,
        series: [
          {
            name: '金额(￥)',
            type: 'pie',
            roseType: 'radius',
            radius: '50%',
            center: ['50%', '50%'],
            data: [
              { value: 13200, name: '门票' },
              { value: 24000, name: '酒店' },
              { value: 14900, name: '包租车' },
              { value: 10000, name: '导游预约' },
              { value: 5900, name: '农家乐' },
              { value: 5900, name: '寻美食' },
              { value: 15900, name: '特产购买' },
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
