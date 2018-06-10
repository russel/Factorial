public class Factorial {
    public static ulong iterative(ulong n) {
        ulong total = 1;
        for (ulong i = 2; i <= n; ++i) { total *= i; }
        return total;
    }

    public static ulong recursive(ulong n) {
        return n <= 1 ? 1 : n * recursive(n - 1);
    }

    private static ulong tailRecursive_iterate(ulong n, ulong result) {
        return n <= 1 ? result : tailRecursive_iterate(n - 1, result * n);
    }

    public static ulong tailRecursive(ulong n) {
        return n <= 1 ? 1 : tailRecursive_iterate(n, 1);
    }
}
