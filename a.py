import requests

def get_incomplete_tasks(url):
    try:
        response = requests.get(url)
        response.raise_for_status()  # Raise an exception for bad status codes
        data = response.json()
        
        incomplete_tasks = [task for task in data if not task.get('completed', False)]
        
        return incomplete_tasks
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")
        return []

# Example usage:
url = "https://api.example.com/tasks"
incomplete_tasks = get_incomplete_tasks(url)
print("Incomplete tasks:")
for task in incomplete_tasks:
    print(task)
  
