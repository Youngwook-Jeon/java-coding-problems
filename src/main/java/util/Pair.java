package util;

public final class Pair<T, S> {

    final T x;
    final S y;

    public Pair(T x, S y) {
        this.x = x;
        this.y = y;
    }

    public static <T, S> Pair<T, S> of(T x, S y) {
        return new Pair<>(x, y);
    }

    @Override
    public int hashCode() {
        return x.hashCode() ^ y.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair obj = (Pair) o;
        return this.x.equals(obj.x)
                && this.y.equals(obj.y);
    }
}
