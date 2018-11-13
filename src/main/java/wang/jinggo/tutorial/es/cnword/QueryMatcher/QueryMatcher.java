package wang.jinggo.tutorial.es.cnword.QueryMatcher;

import wang.jinggo.tutorial.es.cnword.template.AdjList;
import wang.jinggo.tutorial.es.cnword.template.CnToken;
import wang.jinggo.tutorial.es.cnword.template.CnTokenLinkedList;
import wang.jinggo.tutorial.es.cnword.template.QueryTemplate;

/**
 * @author wangyj
 * @description
 * @create 2018-11-13 11:31
 **/
public class QueryMatcher {

    QueryTemplate grammar; //问句文法库

    public QueryMatcher(){
        grammar = new QueryTemplate();
    }

    /**
     * 增加一个问句模板到模板库
     * @param query 标准查询
     * @param template 问句模板
     */
    public void add(String query,String template){
        grammar.add(query , template);
    }

    public String match(String input){
        AdjList g = grammar.getLattice(input);
        //System.out.println("图 "+g);

        CnTokenLinkedList ll = g.edges(0);

        for(CnToken t :ll){
            if(t.end==input.length()){ //整句匹配
                return t.types.iterator().next().type;
            }
        }
        return null;
    }
}
