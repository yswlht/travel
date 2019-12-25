<template>
    <div class="chart">
        <pie-chart />
    </div>
</template>
<script>
import PieChart from './components/PieChart'
export default {
    name:'count',
    components:{ PieChart },
}
</script>
<style scoped>
    .chart{
        /* background-color: aqua; */
        position: relative;
        /* margin-top: 5%; */
        height: 900px;
        width: 100%;
    }
</style>
