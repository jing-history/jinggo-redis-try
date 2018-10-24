**1. curl 的使用**

// curl 使用 Window 下需要 ^ 符号
curl -XPOST http://192.168.5.111:9200/product/book/1?pretty -H Content-Type:application/json -d'^
{^
	"name" : "china food",^
	"type" : "food",^
	"postDate" : "2018-10-25",^
	"message" : "介绍啥子东西"^
}^
'
//新增内容
curl -XPOST http://192.168.5.111:9200/product/book/1?pretty -H Content-Type:application/json -d'
{
	"name" : "china food",
	"type" : "food",
	"postDate" : "2018-10-25",
	"message" : "介绍啥子东西"
}'

//主键查询
curl -XGET http://192.168.5.111:9200/product/book/1?pretty

//更新内容
curl -XPUT http://192.168.5.111:9200/product/book/1?pretty -H Content-Type:application/json -d'
{
	"name" : "广州100种小吃",
	"type" : "food",
	"postDate" : "2018-10-25",
	"message" : "比如猪脚饭，拉面"
}'

//局部更新
curl -XPOST http://192.168.5.111:9200/product/book/1?pretty -H Content-Type:application/json -d'
{
	"doc" : {
		"message" : "比如猪脚饭，拉面，蛋炒饭"
	}
}'

//根据主键删除文档
curl -XDELETE http://192.168.5.111:9200/product/book/1?pretty

//搜索文档 下面get 方式好像不对 -G = get
curl -G --data-urlencode 'q=message:拉面' http://192.168.5.111:9200/product/book/_search?pretty

//POST 方式
curl -XPOST http://192.168.5.111:9200/product/book/_search?pretty -H Content-Type:application/json -d'
{
	"query" : {
		"match" : {"message" : "拉面"}
	}
}'

//精确查询
curl -XGET http://192.168.5.111:9200/product/book/_search?pretty -H Content-Type:application/json -d'
{
	"query" : {
		"term" : {"type" : "food"}
	}
}'
 
//精确查询 需要完成翻页功能
curl -XGET http://192.168.5.111:9200/product/book/_search?pretty -H Content-Type:application/json -d'
{
	"from":0,"size":5,
	"query" : {
		"term" : {"type" : "food"}
	}
}'

//查询总数
curl -XGET http://192.168.5.111:9200/product/book/_count -H Content-Type:application/json -d'
{
	"query" : {
		"term" : {"type" : "food"}
	}
}'

//联合条件查询 使用must
curl -XGET http://192.168.5.111:9200/product/book/_search?pretty -H Content-Type:application/json -d'
{
	"from":0,"size":5,
	"query" : {
		"bool": {
			"must": [
			  {"match":{"message" : "拉面"}},
			  {"term":{"type" : "food"}}
			]
		}
	}
}'

YELLOW (参考连接:http://www.jwsblog.com/archives/59.html)
原因
yellow表示所有主分片可用，但不是所有副本分片都可用，最常见的情景是单节点时，由于es默认是有1个副本，
主分片和副本不能在同一个节点上，所以副本就是未分配unassigned

处理
过滤查看所有未分配索引的方式， 
curl -s "http://192.168.5.111:9200/_cat/shards" | grep UNASSIGNED 结果如下，
第一列表示索引名，第二列表示分片编号，第三列p是主分片，r是副本

分配分片
知道哪个索引的哪个分片就开始手动修复，通过reroute的allocate分配

curl -XPOST '192.168.5.111:9200/_cluster/reroute' -d '{
    "commands" : [ {
          "allocate" : {
              "index" : "product",
              "shard" : 5,
              "node" : "p1",
              "allow_primary" : true
          }
        }
    ]
}'

查看ES各个分片的状态
$ curl -XGET http://192.168.5.111:9200/_cluster/health?pretty

---
2. 日志输入格式化
java -jar xxx.jar --logging.file=xxx.log

---
**死磕Elasticsearch方法论**
https://github.com/laoyang360/deep_elasticsearch/wiki/%E3%80%8A%E6%AD%BB%E7%A3%95Elasticsearch%E6%96%B9%E6%B3%95%E8%AE%BA%E3%80%8B%E2%80%94%E2%80%94%E9%93%AD%E6%AF%85%E5%A4%A9%E4%B8%8B%E5%87%BA%E5%93%81

text类型和keyword类型的存储字符数区别
text类型：支持分词、全文检索，不支持聚合、排序操作。 
适合大字段存储，如：文章详情、content字段等；

keyword类型：支持精确匹配，支持聚合、排序操作。 
适合精准字段匹配，如：url、name、title等字段。 
一般情况，text和keyword共存，设置mapping如下：
"id": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        }