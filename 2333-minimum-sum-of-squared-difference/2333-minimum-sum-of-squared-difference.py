class Solution:
    def minSumSquareDiff(self, nums1: list[int], nums2: list[int],
                         k1: int, k2: int) -> int:
        k = k1 + k2
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]

        if sum(diff) <= k:
            return 0

        low, high = 0, max(diff)

        # Find the smallest level we can reduce all differences to
        while low < high:
            mid = (low + high) // 2
            needed = sum(max(0, d - mid) for d in diff)

            if needed <= k:
                high = mid
            else:
                low = mid + 1

        level = low
        remaining = k
        answer = 0

        # Reduce all differences above this level
        for d in diff:
            if d > level:
                remaining -= d - level
                d = level
            answer += d * d

        # Spend remaining operations reducing level-sized differences
        for d in diff:
            if remaining == 0:
                break
            if d >= level and level > 0:
                answer -= level * level - (level - 1) ** 2
                remaining -= 1

        return answer