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

---
2. 日志输入格式化
java -jar xxx.jar --logging.file=xxx.log