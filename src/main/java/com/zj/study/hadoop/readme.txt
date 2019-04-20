注意配置装有hadoop的机器的hosts，要加上自己的ip映射，否则不成功


hadoop jar wc.jar com.zj.study.hadoop.wordcount.WCRunner

hadoop jar wc.jar com.zj.study.hadoop.sortMR.SortFlowSumRunner /
hadoop jar sort.jar com.zj.study.hadoop.sortMR.SortFlowSumRunner /flow/srcdata /flow/output1
hadoop jar area.jar com.zj.study.hadoop.areaMR.AreaSumRunner /flow/srcdata /flow/output