package auth;

public class TokenManager {
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static void setToken(String t) { token.set(t); }
    public static String getToken() { return token.get(); }
    public static void clear() { token.remove(); }
}