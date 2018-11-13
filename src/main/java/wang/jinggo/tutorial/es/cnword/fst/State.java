package wang.jinggo.tutorial.es.cnword.fst;

import java.io.Serializable;
import java.util.*;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 20:37
 **/
public class State implements Serializable, Comparable<State> {


    //public HashSet<String> wordType; //词的类型
    //public HashSet<String> automatonType; //自动机类型,自动机类型匹配最长的
    public HashMap<String,Byte> automaton2WordType;

    Set<Transition> transitions;

    int number;

    public int id;
    public static int next_id;

    /**
     * Constructs a new state. Initially, the new state is a reject state.
     */
    public State() {
        resetTransitions();
        id = next_id++;
    }

    /**
     * Resets transition set.
     */
    final void resetTransitions() {
        transitions = new HashSet<Transition>();
    }

    /**
     * Returns the set of outgoing transitions.
     * Subsequent changes are reflected in the automaton.
     * @return transition set
     */
    public Set<Transition> getTransitions()	{
        return transitions;
    }

    /**
     * Adds an outgoing transition.
     * @param t transition
     */
    public void addTransition(Transition t)	{
        transitions.add(t);
    }

    /**
     * Performs lookup in transitions, assuming determinism.
     * @param c character to look up
     * @return destination state, null if no matching outgoing transition
     */
    public State step(char c) {
        //TODO 组织成二叉搜索树的形式
        for (Transition t : transitions)
            if (t.min <= c && c <= t.max)
                return t.to;
        return null;
    }

    void addEpsilon(State to) {
        //TODO
        if (to.automaton2WordType!=null)
            automaton2WordType = to.automaton2WordType;
        for (Transition t : to.transitions)
            transitions.add(t);
    }

    /** Returns transitions sorted by (min, reverse max, to) or (to, min, reverse max) */
    Transition[] getSortedTransitionArray(boolean to_first) {
        Transition[] e = transitions.toArray(new Transition[transitions.size()]);
        Arrays.sort(e, new TransitionComparator(to_first));
        return e;
    }

    /**
     * Returns sorted list of outgoing transitions.
     * @param to_first if true, order by (to, min, reverse max); otherwise (min, reverse max, to)
     * @return transition list
     */
    public List<Transition> getSortedTransitions(boolean to_first)	{
        return Arrays.asList(getSortedTransitionArray(to_first));
    }

    /**
     * Returns string describing this state. Normally invoked via
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("state ").append(number);
        if (automaton2WordType!=null)
            b.append(" [accept]");
        else
            b.append(" [reject]");
        b.append(":\n");
        for (Transition t : transitions)
            b.append("  ").append(t.toString()).append("\n");
        return b.toString();
    }

    /**
     * Compares this object with the specified object for order.
     * States are ordered by the time of construction.
     */
    public int compareTo(State s) {
        return s.id - id;
    }

    /**
     * See {@link java.lang.Object#equals(java.lang.Object)}.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * See {@link java.lang.Object#hashCode()}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public State step(CharSpan c) {
        for (Transition t : transitions)
            if (t.min <= c.min && c.max <= t.max)
                return t.to;
        return null;
    }

    public boolean isAccept() {
        return (automaton2WordType!=null);
    }
}
