export async function saveVisitorInfo() {
    try {
        // Send a POST request without a request body.
        const response = await fetch('https://itay-olivcovitz.up.railway.app/growhire/visitor/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            console.error(`Failed to save visitor info. Status: ${response.status}, Message: ${errorMessage}`);
            return;
        }

        const visitor = await response.json();
        //console.log('Visitor info saved successfully:', visitor);
    } catch (error) {
        //console.error('Error saving visitor info:', error.message);
    }
}
