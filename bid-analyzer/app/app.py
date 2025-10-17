# app.py
from flask import Flask, request, jsonify
from flask_cors import CORS
import subprocess
import os
from pathlib import Path

app = Flask(__name__)
CORS(app)  # React에서 접근 가능하도록

@app.route('/convert', methods=['POST'])
def convert_hwp_to_txt():
    try:
        data = request.get_json()
        hwp_path = data.get('file_path')
        print("1")
        print(hwp_path)
        if not hwp_path:
            return jsonify({'error': '파일 경로가 제공되지 않았습니다'}), 400
        
        # 파일 존재 확인
        if not os.path.exists(hwp_path):
            return jsonify({'error': '파일을 찾을 수 없습니다'}), 404
        
        # HWP 파일인지 확인
        if not hwp_path.lower().endswith('.hwp'):
            return jsonify({'error': 'HWP 파일만 변환 가능합니다'}), 400
        
        # 출력 경로 설정 (같은 디렉토리)
        output_dir = os.path.dirname(hwp_path)
        
        # LibreOffice로 변환
        cmd = [
            'libreoffice',
            '--headless',
            '--convert-to', 'txt:Text',
            '--outdir', output_dir,
            hwp_path
        ]
        
        result = subprocess.run(
            cmd, 
            capture_output=True, 
            text=True, 
            timeout=60
        )
        
        if result.returncode != 0:
            return jsonify({
                'error': '변환 실패',
                'details': result.stderr
            }), 500
        
        # 변환된 파일 경로
        txt_filename = os.path.splitext(os.path.basename(hwp_path))[0] + '.txt'
        txt_path = os.path.join(output_dir, txt_filename)
        
        # 변환된 파일 존재 확인
        if not os.path.exists(txt_path):
            return jsonify({'error': '변환된 파일을 찾을 수 없습니다'}), 500
        
        return jsonify({
            'success': True,
            'message': '변환 완료',
            'original_path': hwp_path,
            'converted_path': txt_path,
            'filename': txt_filename
        }), 200
        
    except subprocess.TimeoutExpired:
        return jsonify({'error': '변환 시간 초과'}), 500
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@app.route('/convert-batch', methods=['POST'])
def convert_batch():
    try:
        data = request.get_json()
        file_paths = data.get('file_paths', [])
        
        if not file_paths:
            return jsonify({'error': '파일 경로가 제공되지 않았습니다'}), 400
        
        results = []
        
        for hwp_path in file_paths:
            try:
                if not os.path.exists(hwp_path):
                    results.append({
                        'path': hwp_path,
                        'success': False,
                        'error': '파일을 찾을 수 없습니다'
                    })
                    continue
                
                output_dir = os.path.dirname(hwp_path)
                
                cmd = [
                    'libreoffice',
                    '--headless',
                    '--convert-to', 'txt:Text',
                    '--outdir', output_dir,
                    hwp_path
                ]
                
                result = subprocess.run(
                    cmd,
                    capture_output=True,
                    text=True,
                    timeout=60
                )
                
                if result.returncode == 0:
                    txt_filename = os.path.splitext(os.path.basename(hwp_path))[0] + '.txt'
                    txt_path = os.path.join(output_dir, txt_filename)
                    
                    results.append({
                        'path': hwp_path,
                        'success': True,
                        'converted_path': txt_path
                    })
                else:
                    results.append({
                        'path': hwp_path,
                        'success': False,
                        'error': result.stderr
                    })
                    
            except Exception as e:
                results.append({
                    'path': hwp_path,
                    'success': False,
                    'error': str(e)
                })
        
        return jsonify({
            'results': results,
            'total': len(file_paths),
            'success_count': sum(1 for r in results if r['success'])
        }), 200
        
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@app.route('/health', methods=['GET'])
def health_check():
    return jsonify({'status': 'ok'}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
