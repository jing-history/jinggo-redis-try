package wang.jinggo.tutorial.es.cnword.fst;

import wang.jinggo.tutorial.es.automaton.Automaton;
import wang.jinggo.tutorial.es.cnword.biseg.CnToken;
import wang.jinggo.tutorial.es.cnword.fstOperator.FSTStatePair;
import wang.jinggo.tutorial.es.cnword.fstOperator.FSTUnion;
import wang.jinggo.tutorial.es.cnword.fstOperator.StatePair;

import java.util.*;

/**
 * 把 FSM转换成FST,然后再根据上下文把基本类型转换成最终的类型
 * @author wangyj
 * @description
 * @create 2018-11-12 20:36
 **/
public class FST {

    public State initial;

    static int id=0;  //用于得到唯一的名字

    //词性
    public FST(Automaton a, byte type) throws Exception {
        if (!a.deterministic) {
            throw new Exception("Automaton必须是DFA");
        }

        String name=String.valueOf(id++);
        HashMap<wang.jinggo.tutorial.es.automaton.State, State> m = new HashMap<wang.jinggo.tutorial.es.automaton.State, State>();
        Set<wang.jinggo.tutorial.es.automaton.State> states = a.getStates();
        for (wang.jinggo.tutorial.es.automaton.State s : states)
            m.put(s, new State());
        for (wang.jinggo.tutorial.es.automaton.State s : states) {
            State p = m.get(s);

            if (s.accept) {
                //p.wordType = new HashSet<String>();
                //p.automatonType = new HashSet<String>();
                p.automaton2WordType = new HashMap<String,Byte>();

                if(type>=0){
                    //p.wordType.add(type);
                    //p.automatonType.add(name);
                    p.automaton2WordType.put(name, type);
                }
            }
            if (s == a.getInitialState()) {
                initial = p;
            }
            for (wang.jinggo.tutorial.es.automaton.Transition t : s.transitions) {
                p.transitions.add(new Transition(t.min, t.max, m.get(t.to)));
            }
        }
    }

    public static Set<CharSpan> getCharSpans(
            Set<wang.jinggo.tutorial.es.cnword.fst.Transition> transitions) {
        HashSet<CharSpan> charSpans = new HashSet<CharSpan>();
        for (wang.jinggo.tutorial.es.cnword.fst.Transition t : transitions) {
            CharSpan cs = new CharSpan(t.min, t.max);
            charSpans.add(cs);
        }
        return charSpans;
    }

    public FST(FSTStatePair fstPair) {
        HashMap<StatePair, State> m = new HashMap<StatePair, State>();
        Set<StatePair> states = fstPair.getStates();
        for (StatePair s : states)
            m.put(s, new State());
        for (StatePair s : states) {
            State p = m.get(s);

            //p.wordType = s.wordType;
            //p.automatonType = s.automatonType;
            p.automaton2WordType = s.automaton2WordType;
            if (s == fstPair.getInitialState()) {
                initial = p;
            }

            if (s.transitions == null)
                continue;
            for (wang.jinggo.tutorial.es.cnword.fstOperator.Transition t : s.transitions) {
                p.transitions.add(new Transition(t.min, t.max, m.get(t.to)));
            }
        }
    }

    public FST union(FST f) {
        FSTUnion union = new FSTUnion(this, f);

        return union.union();
    }

    public CnToken matchLong(String s, int offset) {
        State p = initial;
        // System.out.println("initial state "+p);
        int i = offset;
        State toReturn = null;
        int end = offset;
        for (; i < s.length(); i++) {
            State q = p.step(s.charAt(i));
            // System.out.println("new state "+q);
            if (q == null) {
                break;
            }
            p = q;

            if (p.automaton2WordType != null) {
                toReturn = p;
                end = i + 1;
            }
        }
        if (toReturn != null) {
            return new CnToken(s.substring(offset, end), offset, end,
                    toReturn.automaton2WordType.values());
        }
        return null;
    }

    public void count(HashMap<String,String> automaton2WordType,
                      HashMap<String,Integer> count,
                      HashMap<String,Token> tokens,
                      int len,
                      Token token){
        for(Map.Entry<String, String> e:automaton2WordType.entrySet()){
            String automatonName = e.getKey();
            Integer matchLen = count.get(automatonName);

            if(matchLen==null){
                count.put(automatonName, len);
                tokens.put(automatonName, token);
            }
            else if(len>matchLen){
                count.put(automatonName, len);
                tokens.put(automatonName, token);
            }
        }
    }

    /**
     * Returns the set of states that are reachable from the initial state.
     * @return set of {@link State} objects
     */
    public Set<State> getStates() {
        Set<State> visited = new HashSet<State>();

        LinkedList<State> worklist = new LinkedList<State>();
        worklist.add(initial);
        visited.add(initial);
        while (worklist.size() > 0) {
            State s = worklist.removeFirst();
            Collection<Transition> tr = s.transitions;
            for (Transition t : tr)
                if (!visited.contains(t.to)) {
                    visited.add(t.to);
                    worklist.add(t.to);
                }
        }
        return visited;
    }

    /**
     * 分配连续的号码到给定状态
     */
    static void setStateNumbers(Set<State> states) {
        int number = 0;
        for (State s : states)
            s.number = number++;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        Set<State> states = getStates();
        setStateNumbers(states);
        b.append("initial state: ").append(initial.number).append("\n");
        for (State s : states)
            b.append(s.toString());

        return b.toString();
    }
}
