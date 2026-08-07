
class Solution:
    def smallestNumber(self, num: str, t: int) -> str:

        # Factorize t into 2, 3, 5, 7
        need_factor = [0] * 4
        primes = [2, 3, 5, 7]

        for i, p in enumerate(primes):
            while t % p == 0:
                need_factor[i] += 1
                t //= p

        # Any other prime factor is impossible
        if t != 1:
            return "-1"

        a, b, c, d = need_factor

        # Factor contribution of digits 1..9
        factor = [[0] * 4 for _ in range(10)]

        for digit in range(1, 10):
            x = digit

            for i, p in enumerate(primes):
                while x % p == 0:
                    factor[digit][i] += 1
                    x //= p

        # ---------------------------------------------------------
        # dp[x][y][z][w] = minimum number of digits needed to
        # provide at least x factors of 2, y of 3, z of 5, w of 7.
        # ---------------------------------------------------------
        INF = 10**9

        dp = [
            [
                [
                    [INF] * (d + 1)
                    for _ in range(c + 1)
                ]
                for _ in range(b + 1)
            ]
            for _ in range(a + 1)
        ]

        dp[0][0][0][0] = 0

        for x2 in range(a + 1):
            for x3 in range(b + 1):
                for x5 in range(c + 1):
                    for x7 in range(d + 1):

                        cur = dp[x2][x3][x5][x7]

                        if cur == INF:
                            continue

                        for digit in range(2, 10):

                            f2, f3, f5, f7 = factor[digit]

                            nx2 = min(a, x2 + f2)
                            nx3 = min(b, x3 + f3)
                            nx5 = min(c, x5 + f5)
                            nx7 = min(d, x7 + f7)

                            dp[nx2][nx3][nx5][nx7] = min(
                                dp[nx2][nx3][nx5][nx7],
                                cur + 1
                            )

        def min_digits(r2, r3, r5, r7):
            return dp[r2][r3][r5][r7]

        # ---------------------------------------------------------
        # Check if num itself is valid
        # ---------------------------------------------------------
        r2, r3, r5, r7 = a, b, c, d

        valid = True

        for ch in num:
            digit = ord(ch) - ord('0')

            if digit == 0:
                valid = False
                break

            r2 = max(0, r2 - factor[digit][0])
            r3 = max(0, r3 - factor[digit][1])
            r5 = max(0, r5 - factor[digit][2])
            r7 = max(0, r7 - factor[digit][3])

        if valid and r2 == r3 == r5 == r7 == 0:
            return num

        n = len(num)

        # ---------------------------------------------------------
        # Prefix factor counts
        # ---------------------------------------------------------
        pref = [[0] * (n + 1) for _ in range(4)]

        for i, ch in enumerate(num):

            digit = ord(ch) - ord('0')

            for k in range(4):
                pref[k][i + 1] = pref[k][i]

            if digit != 0:
                for k in range(4):
                    pref[k][i + 1] += factor[digit][k]

        # ---------------------------------------------------------
        # Same length answer
        #
        # Change the rightmost possible position first.
        # ---------------------------------------------------------
        for i in range(n - 1, -1, -1):

            # Prefix must be zero-free
            if '0' in num[:i]:
                continue

            original = int(num[i])

            # Try smallest digit > original
            for digit in range(original + 1, 10):

                r2 = max(
                    0,
                    a - pref[0][i] - factor[digit][0]
                )

                r3 = max(
                    0,
                    b - pref[1][i] - factor[digit][1]
                )

                r5 = max(
                    0,
                    c - pref[2][i] - factor[digit][2]
                )

                r7 = max(
                    0,
                    d - pref[3][i] - factor[digit][3]
                )

                suffix_len = n - i - 1

                if min_digits(r2, r3, r5, r7) <= suffix_len:

                    ans = list(num[:i])
                    ans.append(str(digit))

                    # Build smallest possible suffix
                    for pos in range(suffix_len):

                        remaining = suffix_len - pos - 1

                        for x in range(1, 10):

                            nr2 = max(0, r2 - factor[x][0])
                            nr3 = max(0, r3 - factor[x][1])
                            nr5 = max(0, r5 - factor[x][2])
                            nr7 = max(0, r7 - factor[x][3])

                            if min_digits(
                                nr2, nr3, nr5, nr7
                            ) <= remaining:

                                ans.append(str(x))

                                r2, r3 = nr2, nr3
                                r5, r7 = nr5, nr7

                                break

                    return ''.join(ans)

        # ---------------------------------------------------------
        # Same length impossible.
        # Need a longer number.
        # ---------------------------------------------------------
        length = max(
            n + 1,
            min_digits(a, b, c, d)
        )

        ans = []

        r2, r3, r5, r7 = a, b, c, d

        # Build smallest zero-free number
        for pos in range(length):

            remaining = length - pos - 1

            for digit in range(1, 10):

                nr2 = max(0, r2 - factor[digit][0])
                nr3 = max(0, r3 - factor[digit][1])
                nr5 = max(0, r5 - factor[digit][2])
                nr7 = max(0, r7 - factor[digit][3])

                if min_digits(
                    nr2, nr3, nr5, nr7
                ) <= remaining:

                    ans.append(str(digit))

                    r2, r3 = nr2, nr3
                    r5, r7 = nr5, nr7

                    break

        return ''.join(ans)
