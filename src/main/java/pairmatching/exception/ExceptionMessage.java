package pairmatching.exception;

public enum ExceptionMessage {

    FILE_PATH_ERROR("파일의 경로를 찾을 수 없습니다."),
    MAX_TRY_ERROR("최대 입력 가능 횟수를 초과했습니다."),
    NO_COURSE("해당하는 과정이 없습니다."),
    NO_LEVEL("해당하는 레벨이 없습니다."),
    NO_MISSION("해당하는 미션이 없습니다."),
    REPLY_ERROR("네 | 아니오 중 입력해주세요."),
    NO_MATCHING_ERROR("매칭 이력이 없습니다."),
    LEVEL_MISSION_ERROR("레벨과 미션을 재확인해주세요."),
    NO_FUNCTION_ERROR("보기의 기능 중 선택해주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
