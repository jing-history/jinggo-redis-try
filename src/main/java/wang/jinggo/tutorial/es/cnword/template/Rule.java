package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * 从文本中解析出来的规则
 * 
 * @author luogang
 * @2013-12-27
 */
public class Rule {
	public ArrayList<String> rhs = new ArrayList<String>(); // 右边的Token序列
	public HashMap<String, HashSet<WordType>> words = new HashMap<String, HashSet<WordType>>();
	public HashMap<String, String> ruleTag = new HashMap<String, String>();

	public void addWord(String w, String type) {
		HashSet<WordType> ret = words.get(w);
		if (ret == null) {
			ret = new HashSet<WordType>();
			ret.add(new WordType(type));
			words.put(w, ret);
		} else {
			ret.add(new WordType(type));
		}
	}

	public void addWord(String w, String type, String qNo) {
		HashSet<WordType> ret = words.get(w);
		if (ret == null) {
			ret = new HashSet<WordType>();
			ret.add(new WordType(type, qNo));
			words.put(w, ret);
		} else {
			ret.add(new WordType(type, qNo));
		}
	}

	//public void addRule(Nonterminal nonterminal, String type) {
	//	simpleRules.add(new SemRule(nonterminal, type));
	//}

	/*public class SemRule {
		public Nonterminal t;
		public String type;

		public SemRule(Nonterminal t, String type) {
			this.t = t;
			this.type = type;
		}

		@Override
		public String toString() {
			return "SemRule [Nonterminal=" + t + ", type=" + type + "]";
		}
	}*/

	@Override
	public String toString() {
		return "Rule [rhs=" + rhs + ", words="
				+ words + ", ruleTag=" + ruleTag + "]";
	}
}
