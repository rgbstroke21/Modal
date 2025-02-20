import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler

# Generate synthetic dataset
np.random.seed(42)
X = np.random.randn(300, 2)  # Normal data
X = np.vstack([X, np.array([[5, 5], [6, 6], [7, 7]])])  # Adding anomalies

# Standardizing the data
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Apply K-Means
k = 3  # Choose k based on elbow method or domain knowledge
kmeans = KMeans(n_clusters=k, random_state=42)
kmeans.fit(X_scaled)

# Compute distances from cluster centroids
distances = np.linalg.norm(X_scaled - kmeans.cluster_centers_[kmeans.labels_], axis=1)

# Define anomaly threshold (e.g., top 5% of distances)
threshold = np.percentile(distances, 95)
anomalies = X[distances > threshold]

# Plot results
plt.scatter(X[:, 0], X[:, 1], c=kmeans.labels_, cmap='viridis', marker='o', alpha=0.5)
plt.scatter(anomalies[:, 0], anomalies[:, 1], c='red', marker='x', label='Anomalies')
plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1], c='black', marker='D', label='Centroids')
plt.legend()
plt.title("K-Means Anomaly Detection")
plt.show()
