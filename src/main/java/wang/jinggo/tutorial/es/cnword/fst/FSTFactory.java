package wang.jinggo.tutorial.es.cnword.fst;

import wang.jinggo.tutorial.es.automaton.Automaton;
import wang.jinggo.tutorial.es.automaton.BasicAutomata;
import wang.jinggo.tutorial.es.automaton.BasicOperations;
import wang.jinggo.tutorial.es.cnword.biseg.PartOfSpeech;
import wang.jinggo.tutorial.es.cnword.fstOperator.FSTUnion;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 20:23
 **/
public class FSTFactory {

    public static final String BlankType = "blank";

    /**
     * 得到数字、缩略词和大写字母短语的FST
     *
     * @return
     * @throws Exception
     */
    public static FST createUnkown() throws Exception {
        Automaton num = AutomatonFactory.getNum();
        FST numFST = new FST(num, PartOfSpeech.m);
        return numFST;
		/*Automaton capitalPhrase = AutomatonFactory.getCapitalPhrase();
		FST capitalFST = new FST(capitalPhrase, PartOfSpeech.n.name());
		FSTUnion union = new FSTUnion(numFST, capitalFST);

		Automaton composite = AutomatonFactory.getComposite();
		FST compositeFST = new FST(composite, PartOfSpeech.n.name());
		union = new FSTUnion(union.union(), compositeFST);

		Automaton Abbr = AutomatonFactory.getAbbreviation();
		FST abbrFST = new FST(Abbr, PartOfSpeech.n.name());
		FSTUnion unionAll = new FSTUnion(union.union(), abbrFST);
		return unionAll.union();*/
    }

    public static FST createAll() throws Exception {
        FST unknowFST = createUnkown();
        FST simpleFST = createSimple();
        FSTUnion union = new FSTUnion(unknowFST, simpleFST);
        return union.union();
    }

    //英文字符和数字,以及空格
    public static FST createSimple() throws Exception {
        Automaton num = BasicAutomata.makeCharRange('0', '9').repeat(1);
        num.determinize();
        num.minimize();
        // System.out.println(num);

        FST fstNum = new FST(num, PartOfSpeech.m);

        Automaton lowerCase = BasicAutomata.makeCharRange('a', 'z');
        Automaton upperCase = BasicAutomata.makeCharRange('A', 'Z');
        Automaton c = BasicOperations.union(lowerCase, upperCase);
        c.determinize();
        Automaton character = c.repeat(1);
        character.determinize();
        character.minimize();

        //System.out.println(character.toString());

        FST fstN = new FST(character, PartOfSpeech.n);
        FSTUnion union = new FSTUnion(fstNum, fstN);

        FST newFST = union.union();

        Automaton blank = BasicAutomata.makeChar(' ').repeat(1);

        Automaton newLine = BasicAutomata.makeString("\r\n").repeat(1);
        blank = BasicOperations.union(blank, newLine);

        newLine = BasicAutomata.makeChar('\n').repeat(1);
        blank = BasicOperations.union(blank, newLine);

        blank.determinize();

        FST fstBlank = new FST(blank, PartOfSpeech.w);

        union = new FSTUnion(newFST, fstBlank);

        return union.union();
    }
}
