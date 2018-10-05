# jinggo-redis-try
redis 的使用实践

curl 使用
curl -XPOST '192.168.5.111:9300/product/book/1?pretty' -H 'Content-Type:application/json' -d'^
{^
	"name" : "china food",^
	"type" : "food",^
	"postDate" : "2018-10-25",^
	"message" : "介绍啥子东西"^
}^
'

curl -XGET '192.168.5.111:9300/product/book/1?pretty'
