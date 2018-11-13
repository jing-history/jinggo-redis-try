package wang.jinggo.tutorial.es.cnword.fst;

import wang.jinggo.tutorial.es.automaton.Automaton;
import wang.jinggo.tutorial.es.automaton.BasicAutomata;
import wang.jinggo.tutorial.es.automaton.BasicOperations;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 20:36
 **/
public class AutomatonFactory {

    /**
     * 识别数字的有限状态机
     *
     * @return
     */
    public static Automaton getNum() {
        Automaton a = BasicAutomata.makeCharRange('0', '9');
        Automaton b = a.repeat(1);
        Automaton comma = BasicAutomata.makeChar(',');
        Automaton end = BasicOperations.concatenate(comma, a.repeat(1));
        Automaton intNum = BasicOperations.concatenate(b, end.repeat());
        Automaton comma2 = BasicAutomata.makeChar('.');
        Automaton floatNum = BasicOperations.concatenate(comma2, a.repeat(1));
        Automaton intWithFloat = BasicOperations.concatenate(intNum,
                floatNum.optional());
        Automaton num = BasicOperations.union(intWithFloat, floatNum);

        num.determinize();
        return num;
    }

    /**
     * 识别大写开头的名词短语 例如 the New York Animal Medical Center made a study
     *
     * @return
     */
    public static Automaton getCapitalPhrase() {
        Automaton a = BasicAutomata.makeCharRange('A', 'Z');
        Automaton b = BasicAutomata.makeCharRange('a', 'z');
        Automaton start = BasicOperations.concatenate(a.repeat(1), b.repeat());
        Automaton space = BasicAutomata.makeChar(' ');
        Automaton end = BasicOperations.concatenate(space, start.repeat(1));
        Automaton capitalPhrase = BasicOperations.concatenate(start,
                end.repeat());
        capitalPhrase.determinize();
        return capitalPhrase;
    }

    // 缩写 例如P.S.
    public static Automaton getAbbreviation() {
        Automaton a = BasicAutomata.makeCharRange('A', 'Z');
        Automaton start = BasicOperations.concatenate(a,
                BasicAutomata.makeChar('.'));
        start = start.repeat(1);
        start.determinize();
        return start;
    }

    //the most old-school monster mash fun
    public static Automaton getComposite() {
        Automaton a = BasicAutomata.makeCharRange('a', 'z').repeat(1);
        Automaton start = BasicOperations.concatenate(a,
                BasicAutomata.makeChar('-'));
        start = BasicOperations.concatenate(start,a);
        start.determinize();
        return start;
    }

    //日期,例如 Nov. 29
    public static Automaton getDate(){
        Automaton mon = BasicAutomata.makeString("Jan. ");
        mon = mon.union(BasicAutomata.makeString("Feb. "));
        mon = mon.union(BasicAutomata.makeString("Mar. "));
        mon = mon.union(BasicAutomata.makeString("Apr. "));
        mon = mon.union(BasicAutomata.makeString("Jun. "));
        mon = mon.union(BasicAutomata.makeString("Jul. "));
        mon = mon.union(BasicAutomata.makeString("Aug. "));
        mon = mon.union(BasicAutomata.makeString("Sept. "));
        mon = mon.union(BasicAutomata.makeString("Sep. "));
        mon = mon.union(BasicAutomata.makeString("Oct. "));
        mon = mon.union(BasicAutomata.makeString("Nov. "));
        mon = mon.union(BasicAutomata.makeString("Dec. "));

        Automaton num = BasicAutomata.makeCharRange('0', '9').repeat(1,2);

        Automaton suffix = BasicAutomata.makeString("rd");
        suffix = suffix.union(BasicAutomata.makeString("th"));
        suffix = suffix.union(BasicAutomata.makeString("st"));
        suffix = suffix.union(BasicAutomata.makeString("nd"));

        Automaton date = mon.concatenate(num).concatenate(suffix.optional());
        date.determinize();
        return date;
    }
}
