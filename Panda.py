import pandas as pd

# Sample data
data = {
    'a': [1, 1, 1, 2, 2, 2],
    'b': ['x', 'x', 'x', 'y', 'y', 'y'],
    'c': ['foo', 'foo', 'foo', 'bar', 'bar', 'bar'],
    'd': ['one', 'one', 'one', 'two', 'two', 'two'],
    'e': ['new1', 'new2', 'new3', 'new1', 'new2', 'new3'],
    'f': [10, 20, 30, 40, 50, 60]
}

# Create DataFrame
df = pd.DataFrame(data)

# Pivot the DataFrame to achieve the desired result
result_df = df.pivot_table(index=['a', 'b', 'c', 'd'], columns='e', values='f').reset_index()

# Rename columns to match the required output
result_df.columns.name = None  # Remove the column group name
result_df = result_df.rename(columns={'new1': 'new1', 'new2': 'new2', 'new3': 'new3'})

# Display the result
print(result_df)
