package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String FUNCTION_VIEW = "기능을 선택하세요.\n1. 페어 매칭\n2. 페어 조회\n3. 페어 초기화\nQ. 종료";

    private static final String MISSION_VIEW = "과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주";

    private static final String REPLY_VIEW = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";

    public static String readFunction() {
        System.out.println(FUNCTION_VIEW);
        return Console.readLine().trim();
    }

    public static String readMission() {
        System.out.println(MISSION_VIEW);
        return Console.readLine().trim();
    }

    public static String readReply() {
        System.out.println(REPLY_VIEW);
        return Console.readLine().trim();
    }
}
