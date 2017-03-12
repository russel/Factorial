use "collections"

primitive Factorial

        fun iterative(n: I128): I128 =>
                if n < 0 then return 0 end
                var total: I128 = 1
                for i in Range[I128](2, n + 1) do
                        total = total * i
                end
                total

        fun naive_recursive(n: I128): I128 =>
                if n < 0 then return 0 end
                if n < 2 then 1 else n * naive_recursive(n - 1) end

        fun tail_recursive(n: I128): I128 =>
                if n < 0 then return 0 end
                let iterate = {(i: I128, t: I128 = 1): I128 => if i < 2 then t else this(i - 1, i * t) end}
                iterate(n)
