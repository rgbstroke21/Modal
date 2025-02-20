import pandas as pd
import numpy as np
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler

# Load your processed dataset
df = pd.read_csv("your_data.csv")  # Replace with your actual file
batch_dates = df['batch_date']  # Extract batch dates
X = df.drop(columns=['batch_date'])  # Remove batch date for clustering

# Standardize the features (important for K-Means)
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Apply K-Means clustering
k = 3  # Adjust based on Elbow Method or domain knowledge
kmeans = KMeans(n_clusters=k, random_state=42)
kmeans.fit(X_scaled)

# Compute distances from centroids
distances = np.linalg.norm(X_scaled - kmeans.cluster_centers_[kmeans.labels_], axis=1)

# Set anomaly threshold (e.g., top 5% distances)
threshold = np.percentile(distances, 95)

# Identify anomalous batch dates
anomalies = batch_dates[distances > threshold]

# Print or save the anomalous dates
print("Anomalous Batch Dates:")
print(anomalies.to_list())
