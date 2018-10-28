package wang.jinggo.service;

/**
 * mockito 积分系统未完成 mock 模拟数据
 */
public interface CreditSystemService {
    public int getUserCredit(int userId);
    public boolean addCedit(int userId, int score);
}
