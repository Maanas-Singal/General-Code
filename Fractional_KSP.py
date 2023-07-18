### Python implementation of the Fractional Knapsack problem using a greedy algorithm.

def fractional_knapsack(items, capacity):
    # Sort items by their value-to-weight ratio in descending order
    sorted_items = sorted(items, key=lambda x: x[1] / x[0], reverse=True)

    total_value = 0.0
    knapsack = []

    for item in sorted_items:
        weight, value = item
        if capacity >= weight:
            # Take the whole item
            knapsack.append(item)
            total_value += value
            capacity -= weight
        else:
            # Take a fraction of the item to fill the remaining capacity
            fraction = capacity / weight
            knapsack.append((weight, fraction * value))
            total_value += fraction * value
            break

    return total_value, knapsack

# Example usage:
items = [(10, 60), (20, 100), (30, 120)]  # Each item is represented as (weight, value)
capacity = 50
max_value, knapsack_items = fractional_knapsack(items, capacity)
print("Maximum value in the knapsack:", max_value)
print("Items in the knapsack:", knapsack_items)

### In this implementation, the items list contains tuples representing each item's weight and value. 
### The function fractional_knapsack then sorts the items based on their value-to-weight ratio in descending order and iteratively adds items to the knapsack while considering the weight capacity. 
### If the current item can fit entirely, it is added entirely; otherwise, a fraction of it is added to fill the remaining capacity. 
### Finally, the function returns the maximum total value achieved and the items chosen for the knapsack.
