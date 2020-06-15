'''
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?
'''


def find_dups(arr):
    result = []
    i = 0
    in_hand = arr[i]
    ctr = 0
    while ctr < len(arr):
        i = in_hand - 1
        if arr[i] is None:
            result.append(in_hand)
            if i==0:
                i += 1
            loop = False
            while i < len(arr) and (arr[i] is None or arr[i] == 0):
                i += 1
                loop = True
            if i == len(arr):
                i -= 1
                while i > 0 and (arr[i] is None or arr[i] == 0):
                    i -= 1
                    loop = True
            in_hand = arr[i]
            if loop == True:
                arr[i] = 0
        else:
            if in_hand == arr[in_hand-1]:
                arr[i] = None
                if i == 0:

                    i += 1
                loop = False
                while i < len(arr) and (arr[i] is None or arr[i] == 0):
                    i += 1
                    loop = True
                if i == len(arr):
                    i -= 1
                    while i>0 and (arr[i] is None or arr[i] == 0):
                        i -= 1
                        loop = True

                in_hand = arr[i]
                if loop == True:
                    arr[i] = 0
            else:
                if i == 0:
                    arr[i] = None
                    i += 1
                while i < len(arr) and (arr[i] is None or arr[i] == 0):
                    i += 1
                in_hand = arr[i]
                arr[i] = None
        ctr += 1
    return result


if __name__ == '__main__':
    result = find_dups([4, 3, 2, 7, 8, 2, 3, 1])
    print(result)
