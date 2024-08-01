import pandas as pd

# Example DataFrame
data = {
    'a': [1, 1, 2, 3, 3],
    'b': ['value1', '', 'value2', '', 'value3'],
    'c': ['', 'value2', '', 'value5', ''],
    'd': ['', '', 'value6', 'value7', '']
}

df = pd.DataFrame(data)

# Display original DataFrame
print("Original DataFrame:")
print(df)

# Define a function to combine non-empty values
def combine_non_empty(series):
    # Join non-empty values with a separator (e.g., ', ')
    return ', '.join(series[series != ''])

# Group by 'a' and apply the combine_non_empty function to each column
df_combined = df.groupby('a').agg({
    'b': combine_non_empty,
    'c': combine_non_empty,
    'd': combine_non_empty
}).reset_index()

# Display the DataFrame after combining non-empty values
print("\nDataFrame after combining non-empty values:")
print(df_combined)
