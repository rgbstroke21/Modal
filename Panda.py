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

# Define a function to reshape the group into a single row
def reshape_group(group):
    new_columns = {}
    for idx, row in group.iterrows():
        new_columns[row['e']] = row['f']
    return pd.Series(new_columns)

# Group by a, b, c, d and apply the reshape function
result_df = df.groupby(['a', 'b', 'c', 'd']).apply(reshape_group).reset_index()

# Display the result
print(result_df)
