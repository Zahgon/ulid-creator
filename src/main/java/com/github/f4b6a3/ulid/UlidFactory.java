/*
 * MIT License
 * 
 * Copyright (c) 2020-2023 Fabio Lima
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.f4b6a3.ulid;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;

/**
 * A class that actually generates ULIDs.
 * <p>
 * This class is used by {@link UlidCreator}.
 * <p>
 * You can use this class if you need to use a specific random generator
 * strategy. However, most people just need {@link UlidCreator}.
 * <p>
 * Instances of this class can behave in one of two ways: monotonic or
 * non-monotonic (default).
 * <p>
 * If the factory is monotonic, the random component is incremented by 1 if more
 * than one ULID is generated within the same millisecond.
 * <p>
 * The maximum ULIDs that can be generated per millisecond is 2^80.
 */
public final class UlidFactory {

    private final LongSupplier timeFunction;

    private final LongFunction<Ulid> ulidFunction;

    private final ReentrantLock lock = new ReentrantLock();

    // ******************************
    // Constructors
    // ******************************
    /**
     * Default constructor.
     */
    public UlidFactory() {
        this(new UlidFunction());
    }

    private UlidFactory(LongFunction<Ulid> ulidFunction) {
        this(ulidFunction, System::currentTimeMillis);
    }

    private UlidFactory(LongFunction<Ulid> ulidFunction, LongSupplier timeFunction) {
        Objects.requireNonNull(ulidFunction, "ULID function must not be null");
        Objects.requireNonNull(timeFunction, "Time function must not be null");
        this.ulidFunction = ulidFunction;
        this.timeFunction = timeFunction;
        if (this.ulidFunction instanceof MonotonicFunction) {
            // initialize the internal state of the monotonic function
            ((MonotonicFunction) this.ulidFunction).initialize(this.timeFunction);
        }
    }

    /**
     * Returns a new factory.
     * <p>
     * It is equivalent to the default constructor {@code new UlidFactory()}.
     *
     * @return {@link UlidFactory}
     */
    public static UlidFactory newInstance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new factory.
     *
     * @param random a {@link Random} generator
     * @return {@link UlidFactory}
     */
    public static UlidFactory newInstance(Random random) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new factory.
     * <p>
     * The given random function must return a long value.
     *
     * @param randomFunction a random function that returns a long value
     * @return {@link UlidFactory}
     */
    public static UlidFactory newInstance(LongSupplier randomFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new factory.
     * <p>
     * The given random function must return a byte array.
     *
     * @param randomFunction a random function that returns a byte array
     * @return {@link UlidFactory}
     */
    public static UlidFactory newInstance(IntFunction<byte[]> randomFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     *
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     *
     * @param random a {@link Random} generator
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(Random random) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     * <p>
     * The given random function must return a long value.
     *
     * @param randomFunction a random function that returns a long value
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(LongSupplier randomFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     * <p>
     * The given random function must return a byte array.
     *
     * @param randomFunction a random function that returns a byte array
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(IntFunction<byte[]> randomFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     *
     * @param random       a {@link Random} generator
     * @param timeFunction a function that returns the current time in milliseconds,
     *                     measured from the UNIX epoch of 1970-01-01T00:00Z (UTC)
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(Random random, LongSupplier timeFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     * <p>
     * The given random function must return a long value.
     *
     * @param randomFunction a random function that returns a long value
     * @param timeFunction   a function that returns the current time in
     *                       milliseconds, measured from the UNIX epoch of
     *                       1970-01-01T00:00Z (UTC)
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(LongSupplier randomFunction, LongSupplier timeFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new monotonic factory.
     * <p>
     * The given random function must return a byte array.
     *
     * @param randomFunction a random function that returns a byte array
     * @param timeFunction   a function that returns the current time in
     *                       milliseconds, measured from the UNIX epoch of
     *                       1970-01-01T00:00Z (UTC)
     * @return {@link UlidFactory}
     */
    public static UlidFactory newMonotonicInstance(IntFunction<byte[]> randomFunction, LongSupplier timeFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ******************************
    // Public methods
    // ******************************
    /**
     * Returns a new ULID.
     *
     * @return a ULID
     */
    public Ulid create() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a new ULID.
     *
     * @param time the current time in milliseconds, measured from the UNIX epoch of
     *             1970-01-01T00:00Z (UTC)
     * @return a ULID
     */
    public Ulid create(final long time) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ******************************
    // Package-private inner classes
    // ******************************
    /**
     * Function that creates ULIDs.
     */
    static final class UlidFunction implements LongFunction<Ulid> {

        private final IRandom random;

        private UlidFunction(IRandom random) {
            this.random = random;
        }

        public UlidFunction() {
            this(IRandom.newInstance());
        }

        public UlidFunction(Random random) {
            this(IRandom.newInstance(random));
        }

        public UlidFunction(LongSupplier randomFunction) {
            this(IRandom.newInstance(randomFunction));
        }

        public UlidFunction(IntFunction<byte[]> randomFunction) {
            this(IRandom.newInstance(randomFunction));
        }

        @Override
        public Ulid apply(final long time) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Function that creates Monotonic ULIDs.
     */
    static final class MonotonicFunction implements LongFunction<Ulid> {

        private Ulid lastUlid;

        private final IRandom random;

        // Used to preserve monotonicity when the system clock is
        // adjusted by NTP after a small clock drift or when the
        // system clock jumps back by 1 second due to leap second.
        static final int CLOCK_DRIFT_TOLERANCE = 10_000;

        private MonotonicFunction(IRandom random) {
            this.random = random;
        }

        public MonotonicFunction() {
            this(IRandom.newInstance());
        }

        public MonotonicFunction(Random random) {
            this(IRandom.newInstance(random));
        }

        public MonotonicFunction(LongSupplier randomFunction) {
            this(IRandom.newInstance(randomFunction));
        }

        public MonotonicFunction(IntFunction<byte[]> randomFunction) {
            this(IRandom.newInstance(randomFunction));
        }

        void initialize(LongSupplier timeFunction) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Ulid apply(final long time) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static interface IRandom {

        public long nextLong();

        public byte[] nextBytes(int length);

        static IRandom newInstance() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static IRandom newInstance(Random random) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static IRandom newInstance(LongSupplier randomFunction) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static IRandom newInstance(IntFunction<byte[]> randomFunction) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class LongRandom implements IRandom {

        private final LongSupplier randomFunction;

        public LongRandom() {
            this(newRandomFunction(null));
        }

        public LongRandom(Random random) {
            this(newRandomFunction(random));
        }

        public LongRandom(LongSupplier randomFunction) {
            this.randomFunction = randomFunction != null ? randomFunction : newRandomFunction(null);
        }

        @Override
        public long nextLong() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public byte[] nextBytes(int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static LongSupplier newRandomFunction(Random random) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class ByteRandom implements IRandom {

        private final IntFunction<byte[]> randomFunction;

        public ByteRandom() {
            this(newRandomFunction(null));
        }

        public ByteRandom(Random random) {
            this(newRandomFunction(random));
        }

        public ByteRandom(IntFunction<byte[]> randomFunction) {
            this.randomFunction = randomFunction != null ? randomFunction : newRandomFunction(null);
        }

        @Override
        public long nextLong() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public byte[] nextBytes(int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static IntFunction<byte[]> newRandomFunction(Random random) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
