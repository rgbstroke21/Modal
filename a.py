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
  


import requests

def get_json_response_size(url):
    try:
        response = requests.head(url)
        if response.status_code == 200:
            content_length = response.headers.get('content-length')
            if content_length:
                size_in_bytes = int(content_length)
                return size_in_bytes
            else:
                return "Content length not provided in response headers."
        else:
            return f"Failed to fetch response. Status code: {response.status_code}"
    except requests.exceptions.RequestException as e:
        return f"An error occurred: {e}"

# Example usage:
url = "https://api.example.com/data"
size = get_json_response_size(url)
print("Size of JSON response:", size, "bytes")
